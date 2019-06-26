package com.kutluoglu.pulselivedemo.base

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.kutluoglu.presentation.viewModels.PulseLiveViewModelFactory
import com.kutluoglu.pulselivedemo.R
import com.kutluoglu.pulselivedemo.base.injection.Injectable
import com.kutluoglu.pulselivedemo.main.MainActivity
import com.kutluoglu.pulselivedemo.utils.NetworkUtils
import com.kutluoglu.pulselivedemo.utils.extensions.positiveButton
import com.kutluoglu.pulselivedemo.utils.extensions.showDialog
import com.kutluoglu.pulselivedemo.utils.extensions.showProgressSpinner
import javax.inject.Inject

open class BaseFragment : Fragment(), Injectable {
    @Inject lateinit var viewModelFactory: PulseLiveViewModelFactory
    @Inject lateinit var network: NetworkUtils

    private var spinnerProgress: AlertDialog? = null
    lateinit var act: MainActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        setHasOptionsMenu(true)
        act  = activity as MainActivity
    }

    fun showSpinner() {
        if(spinnerProgress == null) {
            spinnerProgress = showProgressSpinner()
        }

        spinnerProgress?.let {
            if(!it.isShowing) it.show()
        }

    }

    fun dismissSpinner() {
        spinnerProgress?.let {
            if(it.isShowing) it.dismiss()
        }
    }

    fun showDialog(customMsg: String) {
        context?.showDialog {
            setTitle(getString(R.string.app_name))
            setMessage(customMsg)
            positiveButton()
        }
    }

    fun showDialogWithCustomAction(customMsg: String, builderFunction: AlertDialog.Builder.() -> Any) {
        context?.showDialog {
            setTitle(getString(R.string.app_name))
            setMessage(customMsg)
            builderFunction()
        }
    }


    override fun onDestroy() {
        dismissSpinner()
        super.onDestroy()
    }
}
