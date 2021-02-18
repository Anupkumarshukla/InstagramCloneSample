package com.anupkumar.instagramclonesample.ui.photo

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.anupkumar.instagramclonesample.R
import com.anupkumar.instagramclonesample.ui.base.BaseFragment
import com.anupkumar.instagramclonesample.ui.base.BaseViewModel
import com.anupkumar.instagramclonesample.ui.home.HomeFragment
import com.mindorks.paracamera.Camera
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_photo.*
import javax.inject.Inject

@AndroidEntryPoint
class PhotoFragment : BaseFragment<PhotoViewModel>() {

    companion object{
        const val TAG = "PhotoFragment"
        const val RESULT_GALLERY_IMG = 1001
        fun newInstance(): PhotoFragment {
            val args = Bundle()
            val fragment = PhotoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var camera: Camera

    override fun provideLayoutId(): Int  = R.layout.fragment_photo

/*    override fun injectDependencies(fragmentComponent: FragmentComponent) {
        fragmentComponent.inject(this)
    }*/

    override fun setupView(view: View) {
        view_gallery.setOnClickListener {
            Intent(Intent.ACTION_PICK).apply {
                type = "image/*"
            }.let {
                startActivityForResult(it, RESULT_GALLERY_IMG)
            }
        }

        view_camera.setOnClickListener {
            try {
                camera.takePicture()
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        if(resultCode == RESULT_OK){
            when(requestCode){
                RESULT_GALLERY_IMG -> {
                    try {
                        intent?.data?.let {
                            activity?.contentResolver?.openInputStream(it)?.let { it ->
                                viewModel.onGalleryImageSelected(it)
                            }?: showMessage(R.string.try_again)
                        }
                    }catch (e: Exception){
                        e.printStackTrace()
                        showMessage(R.string.try_again)
                    }
                }
                Camera.REQUEST_TAKE_PHOTO -> {
                    viewModel.onCameraImageTaken { camera.cameraBitmapPath }
                }
            }
        }
    }





}