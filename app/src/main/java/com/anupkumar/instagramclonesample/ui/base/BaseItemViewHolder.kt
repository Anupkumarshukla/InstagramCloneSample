package com.anupkumar.instagramclonesample.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.lifecycle.*
import androidx.recyclerview.widget.RecyclerView
import com.anupkumar.instagramclonesample.MyApp
import com.anupkumar.instagramclonesample.di.module.ViewHolderModule
import com.anupkumar.instagramclonesample.utils.display.Toaster
import javax.inject.Inject

abstract class BaseItemViewHolder<T: Any,VM : BaseItemViewModel<T>>(@LayoutRes layoutId: Int,parent: ViewGroup) :
RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(layoutId,parent,false)), LifecycleOwner {

    @Inject
    lateinit var viewModel: VM

    @Inject
    lateinit var lifecycleRegistry:LifecycleRegistry

     override fun getLifecycle(): Lifecycle = lifecycleRegistry

    init {
        onCreate()
    }

     open fun bind(data: T){
         viewModel.updateData(data)
     }

    protected fun onCreate(){
       // injectDependencies(buildViewHolderComponent())
        lifecycleRegistry.setCurrentState(Lifecycle.State.INITIALIZED)
        lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED)
        setupObservers()
        setupView(itemView)
    }

    fun onStart() {
        lifecycleRegistry.setCurrentState(Lifecycle.State.STARTED)
        lifecycleRegistry.setCurrentState(Lifecycle.State.RESUMED)
    }

    fun onStop() {
        lifecycleRegistry.setCurrentState(Lifecycle.State.STARTED)
        lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED)
    }

    fun onDestroy() {
        lifecycleRegistry.setCurrentState(Lifecycle.State.DESTROYED)
    }




/*    private  fun buildViewHolderComponent() =
        DaggerViewHolderComponent
            .builder()
            .applicationComponent((itemView.context.applicationContext as MyApp).applicationComponent)
            .viewHolderModule(ViewHolderModule(this))
            .build()*/

   // protected abstract fun injectDependencies(viewHolderComponent: ViewHolderComponent)
    fun showMessage(message: String) = Toaster.show(itemView.context, message)

    fun showMessage(@StringRes resId: Int) = showMessage(itemView.context.getString(resId))

    protected open fun setupObservers() {
        viewModel.messageString.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })

        viewModel.messageStringId.observe(this, Observer {
            it.data?.run { showMessage(this) }
        })
    }

    abstract fun setupView(view: View)

 }