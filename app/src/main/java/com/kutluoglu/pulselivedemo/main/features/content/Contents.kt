package com.kutluoglu.pulselivedemo.main.features.content

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.kutluoglu.presentation.model.ContentView
import com.kutluoglu.presentation.resource.ResourceState
import com.kutluoglu.presentation.viewModels.content.ContentViewModel
import com.kutluoglu.pulselivedemo.R
import com.kutluoglu.pulselivedemo.base.BaseFragment
import com.kutluoglu.pulselivedemo.utils.extensions.inflate
import com.kutluoglu.pulselivedemo.utils.extensions.setPLTitle
import com.kutluoglu.pulselivedemo.utils.extensions.visibileOrGone
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_contents.*

/**
 * Created by F.K. on 2019-06-25.
 *
 */

class Contents : BaseFragment(), ContentContractor {
    private lateinit var rvAdapter: ContentRvAdapter
    private lateinit var contentViewModel: ContentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Inflate the layout for this fragment
        return container?.inflate(R.layout.fragment_contents)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initializeUI()
        initializeViewModel()
    }
    override fun initializeUI() {
        rvAdapter = ContentRvAdapter { content: ContentView, view: View -> contentClicked(content, view)}
        setContentRv(rvAdapter)
    }

    override fun initializeViewModel() {
        contentViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ContentViewModel::class.java)

        observeContentViewModel()
    }

    private fun observeContentViewModel() {
        contentViewModel.getContentLiveData().observe(this, Observer {
            when(it.status) {
                ResourceState.LOADING -> {
                    showSpinner()
                }
                ResourceState.SUCCESS -> {
                    dismissSpinner()
                    it.data?.let { list ->
                        rvAdapter.setRvData(list)
                        setVisibilityOfRv()
                    }
                }
                ResourceState.ERROR -> {
                    dismissSpinner()
                    showError(it.message)
                }
            }
        })

        contentViewModel.loadContents()
    }

    private fun showError(errorMsg: String?) {
        if (network.hasNotInternet()) {
            val connectionMessage = getString(R.string.connection_error)
            if (rvAdapter.itemCount > 0) {
                Toast.makeText(context, connectionMessage, Toast.LENGTH_SHORT).show()
            } else {
                showDialog(connectionMessage)
            }
        } else {
            setErrorMessage(errorMsg)
        }
    }

    private fun setVisibilityOfRv() {
        if (error_message.visibility == View.VISIBLE) {
            rv_contents.visibileOrGone(true)
            error_message.visibileOrGone(false)
        }

    }

    private fun setErrorMessage(message: String?) {
        error_message.text = message
        error_message.visibileOrGone(true)
        rv_contents.visibileOrGone(false)

    }

    override fun contentClicked(content: ContentView, view: View) {
        gotoContentDetail(content, view)
    }

    private fun gotoContentDetail(content: ContentView, view: View) {
        val action = ContentsDirections.actionContentsToContentDetail(content)
        view.findNavController().navigate(action)
    }

    override fun setContentRv(adapater: ContentRvAdapter) {
        rv_contents.setHasFixedSize(true)
        rv_contents.adapter = adapater
        rv_contents.visibileOrGone(true)
    }

    override fun onResume() {
        super.onResume()
        act.toolbar.setPLTitle(getString(R.string.pulse_live_content))
    }
}