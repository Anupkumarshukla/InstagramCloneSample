package com.anupkumar.instagramclonesample.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.anupkumar.instagramclonesample.R
import com.anupkumar.instagramclonesample.di.component.FragmentComponent
import com.anupkumar.instagramclonesample.ui.base.BaseFragment
import com.anupkumar.instagramclonesample.utils.log.Logger

import javax.inject.Inject

class HomeFragment : BaseFragment<HomeViewModel>(){

    companion object {
        const val TAG = "HomeFragment"
        fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }


    override fun provideLayoutId(): Int = R.layout.fragment_home

    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.loading.observe(this, Observer {
            //progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.posts.observe(this, Observer {
            Logger.d("DEBUG123", it.data.toString())
           // it.data?.run { postsAdapter.appendData(this) }
        })

    }

    override fun setupView(view: View) {}


}