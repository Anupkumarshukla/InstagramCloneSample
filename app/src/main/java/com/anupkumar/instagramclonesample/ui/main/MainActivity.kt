package com.anupkumar.instagramclonesample.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.anupkumar.instagramclonesample.R
import com.anupkumar.instagramclonesample.di.component.ActivityComponent
import com.anupkumar.instagramclonesample.ui.base.BaseActivity
import com.anupkumar.instagramclonesample.ui.home.HomeFragment
import com.anupkumar.instagramclonesample.ui.photo.PhotoFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    companion object{
        const val TAG = "MainActivity"
    }

    private var activeFragment:Fragment? = null

    override fun provideLayoutId(): Int  = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
        bottomNavigation.let {
            it.itemIconTintList = null

            it.setOnNavigationItemSelectedListener{
                when (it.itemId){
                    R.id.itemHome -> {
                        viewModel.onHomeSelected()
                        true
                    }
                    R.id.itemAddPhotos -> {
                        viewModel.onPhotoSelected()
                        true
                    }
                    R.id.itemProfile -> {
                        viewModel.onProfileSelected()
                        true
                    }
                    else -> false
                }
            }
        }
    }


    override fun setupObservers() {
        super.setupObservers()
        viewModel.homeNavigation.observe(this, Observer {
            it.getIfNotHandled()?.let { showHome() }
        })

        viewModel.photoNavigation.observe(this, Observer {
            it.getIfNotHandled()?.let { showAddPhoto() }
        })
    }


    private fun showHome(){
        if (activeFragment is HomeFragment) return
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(HomeFragment.TAG) as HomeFragment?
        if (fragment == null) {
            fragment = HomeFragment.newInstance()
            fragmentTransaction.add(R.id.containerFragment,fragment,HomeFragment.TAG)
        }else{
            fragmentTransaction.show(fragment)
        }
        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)
        fragmentTransaction.commit()
        activeFragment = fragment

    }

    private fun showAddPhoto(){
        if (activeFragment is PhotoFragment) return
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        var fragment = supportFragmentManager.findFragmentByTag(PhotoFragment.TAG) as PhotoFragment?
        if (fragment == null) {
            fragment = PhotoFragment.newInstance()
            fragmentTransaction.add(R.id.containerFragment,fragment,PhotoFragment.TAG)
        }else{
            fragmentTransaction.show(fragment)
        }
        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)
        fragmentTransaction.commit()
        activeFragment = fragment

    }
}