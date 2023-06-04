package com.devmasterteam.tasks.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devmasterteam.tasks.R
import com.devmasterteam.tasks.databinding.ActivityLoginBinding
import com.devmasterteam.tasks.service.model.PersonModel
import com.devmasterteam.tasks.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Variáveis da classe
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        // Layout
        setContentView(binding.root)

        // Eventos
        binding.buttonLogin.setOnClickListener(this)
        binding.textRegister.setOnClickListener(this)

        // Observadores
        observe()
    }

    override fun onClick(v: View) {
        if (v.id == R.id.button_login){
            handleLogin()
        }
    }

    private fun observe() {
        viewModel.login.observe(this) {
            startActivity(Intent(applicationContext, MainActivity::class.java))
        }

        viewModel.failure.observe(this) {
            Toast.makeText(applicationContext, it, Toast.LENGTH_SHORT).show()
        }
    }

    /*
    Caminho de ida: LoginActivity -> LoginViewModel -> PersonRepository -> PersonService -> RetrofitClient -> PersonModel
    Caminho de volta: PersonRepository + Chama o listener.onSucess() ou listener.onFailure() (APIListener suas funções estão override pela LoginViewModel) ->
    LoginViewModel (recebe a chamada de algumas das duas funções) -> LoginActivity fun observe, que esta observando alguma mudança
     */
    private fun handleLogin(){
        val email =  binding.editEmail.text.toString()
        val password = binding.editPassword.text.toString()

        viewModel.doLogin(email,password)
    }

}