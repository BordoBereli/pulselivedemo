package com.kutluoglu.pulselivedemo.main.features.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.kutluoglu.presentation.model.ContentView
import com.kutluoglu.presentation.model.DetailView
import com.kutluoglu.presentation.resource.ResourceState
import com.kutluoglu.presentation.viewModels.detail.ContentDetailViewModel
import com.kutluoglu.pulselivedemo.R
import com.kutluoglu.pulselivedemo.base.BaseFragment
import com.kutluoglu.pulselivedemo.utils.extensions.inflate
import com.kutluoglu.pulselivedemo.utils.extensions.setPLTitle
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.fragment_content_detail.*

/**
 * Created by F.K. on 2019-06-25.
 *
 */

class ContentDetail : BaseFragment(), DetailContract {
    private lateinit var contentDetailViewModel: ContentDetailViewModel
    private lateinit var detail: DetailView
    var content: ContentView? = null
    var isDialogOn = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //Inflate the layout for this fragment
        return container?.inflate(R.layout.fragment_content_detail)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        content = arguments?.let {
            ContentDetailArgs.fromBundle(it).contentView
        }
        initializeViewModel()
    }

    override fun initializeUI(detailView: DetailView) {
        detail = detailView
        setToolbarTitle()
        detail.title.let { detail_title.text = it }
        detail.subtitle.let { detail_subtitle.text = it }
        detail.date.let { detail_date.text = it }
        detail.body.let { detail_body.text = it }
    }

    private fun setToolbarTitle() {
        act.toolbar.setPLTitle(detail.title.let { it })
    }

    override fun initializeViewModel() {
        contentDetailViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ContentDetailViewModel::class.java)

        observeDetailViewModel()
    }

    private fun observeDetailViewModel() {
        contentDetailViewModel.getContentDetailLiveData().observe(this, Observer {
            when(it.status) {
                ResourceState.LOADING -> {
                    showSpinner()
                }
                ResourceState.SUCCESS -> {
                    dismissSpinner()
                    it.data?.let { detailView ->
                        initializeUI(detailView)
                    }
                }
                ResourceState.ERROR -> {
                    dismissSpinner()
                    showError(it.message)
                }
            }
        })

        content?.let {
            contentDetailViewModel.getContentDetailById(it.id.toString())
        }


    }

    private fun showError(message: String?) {
        if (!isDialogOn) {
            isDialogOn = true
            if (network.hasNotInternet()) {
                showDialogWithCustomAction(getString(R.string.connection_error)) {
                    val positiveButton = this.setPositiveButton("Go Back") { _, _ ->
                        view?.findNavController()?.navigateUp()
                        isDialogOn = false
                    }
                    positiveButton
                }
            } else {
                showDialog("$message")
            }
        }
    }
}