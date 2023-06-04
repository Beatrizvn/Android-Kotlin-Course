package com.devmasterteam.tasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.devmasterteam.tasks.service.listener.APIListener
import com.devmasterteam.tasks.service.model.PersonModel
import com.devmasterteam.tasks.service.repository.PersonRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val personRepository = PersonRepository(application.applicationContext)

    private val _login = MutableLiveData<Boolean>()
    val login: LiveData<Boolean> = _login

    private val _failure = MutableLiveData<String>()
    val failure: LiveData<String> = _failure

    /**
     * Faz login usando API
     */
    fun doLogin(email: String, password: String) {
        personRepository.login(email, password, object : APIListener<PersonModel> {
            override fun onSucess(result: PersonModel) {
                _login.value = true
            }

            override fun onFailure(message: String) {
                _failure.value = message
            }
        })

    }

    /**
     * Verifica se usuário está logado
     */
    fun verifyLoggedUser() {


    }

}