package com.anupkumar.instagramclonesample.ui.home

import android.os.Bundle
import android.view.View
import com.anupkumar.instagramclonesample.R
import com.anupkumar.instagramclonesample.di.component.FragmentComponent
import com.anupkumar.instagramclonesample.ui.base.BaseFragment

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

    override fun setupView(view: View) {

    }


}