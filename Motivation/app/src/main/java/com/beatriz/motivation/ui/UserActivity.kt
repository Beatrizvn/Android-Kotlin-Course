package com.beatriz.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.beatriz.motivation.infra.MotivationConstants
import com.beatriz.motivation.R
import com.beatriz.motivation.infra.SecurityPreferences
import com.beatriz.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityUserBinding
    private lateinit var securityPreferences: SecurityPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Esconder a barra
        supportActionBar?.hide()

        // Inicializa variáveis da classe
        securityPreferences = SecurityPreferences(this)

        //Eventos
        binding.buttonSave.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        // val id: Int? = view?.id
        if (view.id == R.id.button_save) {
            handleSave()
        }
    }

    private fun handleSave() {
        val name = binding.editName.text.toString()

        // Verifica se usuário preencheu o nome
        if (name != "") {
            // Salva os dados do usuário e redireciona para as frases
            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)
            // Impede que seja possível voltar a Activity
            finish()
        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }
}