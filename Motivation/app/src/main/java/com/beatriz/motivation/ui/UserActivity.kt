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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Esconder a barra
        supportActionBar?.hide()

        //Eventos
        binding.buttonSave.setOnClickListener(this)

        //verifica se ja tem nome
        verifyUserName()
    }

    override fun onClick(view: View) {
        if (view.id == R.id.button_save) {
            handleSave()
        }
    }

    private fun startMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish() // desativa a anterior, para eu nao conseguir voltar
    }

    private fun verifyUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        if (name != "") {
            startMain()
        }
    }

    private fun handleSave() {
        val name = binding.editName.text.toString()

        if (name != "") {
            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)
            startMain()
        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }
}