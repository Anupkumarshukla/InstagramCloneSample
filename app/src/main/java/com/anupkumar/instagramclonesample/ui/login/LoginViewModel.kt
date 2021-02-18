package com.anupkumar.instagramclonesample.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.anupkumar.instagramclonesample.data.repository.UserRepository
import com.anupkumar.instagramclonesample.ui.base.BaseViewModel
import com.anupkumar.instagramclonesample.utils.common.*
import com.anupkumar.instagramclonesample.utils.network.NetworkHelper
import com.anupkumar.instagramclonesample.utils.rx.SchedulerProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(schedulerProvider: SchedulerProvider,
                                         compositeDisposable: CompositeDisposable,
                                         networkHelper: NetworkHelper,
                                         private val userRepository: UserRepository):BaseViewModel(schedulerProvider,compositeDisposable,networkHelper) {

     val validationsList: MutableLiveData<List<Validation>> = MutableLiveData()

     val launchMain : MutableLiveData<Event<Map<String,String>>> = MutableLiveData()

    val emailField:MutableLiveData<String> = MutableLiveData()
    val passwordField:MutableLiveData<String> = MutableLiveData()
    val loggingIn:MutableLiveData<Boolean> = MutableLiveData()



    val emailValidation: LiveData<Resource<Int>> = filterValidation(Validation.Field.EMAIL)
    val passwordValidation : LiveData<Resource<Int>> = filterValidation(Validation.Field.PASSWORD)




    private fun filterValidation(field : Validation.Field) = Transformations.map(validationsList){
        it.find { validation -> validation.field == field }
            ?.run { return@run this.resource }
            ?: Resource.unknown()
    }

    fun onEmailChange(email : String) = emailField.postValue(email)
    fun onPasswordChange(password : String) = passwordField.postValue(password)



    override fun onCreate() {}

    fun onLogin(){
        val email = emailField.value
        val password = passwordField.value

        val validations = Validator.validateLoginFields(email,password)
        validationsList.postValue(validations)

        if (validations.isNotEmpty() && email != null && password != null) {
            val successValidation = validations.filter { it.resource.status == Status.SUCCESS }
            if(successValidation.size == validations.size && checkInternetConnectionWithMessage()){
                loggingIn.postValue(true)
                compositeDisposable.addAll(
                    userRepository.doUserLogin(email,password)
                        .subscribeOn(schedulerProvider.io())
                        .subscribe(
                            {
                            userRepository.saveCurrentUser(it)
                            loggingIn.postValue(false)
                            launchMain.postValue(Event(emptyMap()))
                            },
                            {
                                handleNetworkError(it)
                                loggingIn.postValue(false)
                            }
                        )
                )
            }
        }
    }



}