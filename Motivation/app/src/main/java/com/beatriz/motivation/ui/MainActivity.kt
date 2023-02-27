package com.beatriz.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import com.beatriz.motivation.infra.MotivationConstants
import com.beatriz.motivation.R
import com.beatriz.motivation.infra.SecurityPreferences
import com.beatriz.motivation.databinding.ActivityMainBinding
import com.beatriz.motivation.data.Mock

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private var filter = MotivationConstants.PHRASEFILTER.ALL
    private lateinit var securityPreferences: SecurityPreferences
    private val mock: Mock = Mock()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Esconder a barra
        supportActionBar?.hide()

        // Inicializa variáveis
        securityPreferences = SecurityPreferences(this)

        //Eventos
        setListeners()

        handleFilter(R.id.image_all)
        handleNewPhrase()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        showUserName()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    /**
     * Função responsavel pelas ações de clique
     */
    override fun onClick(view: View) {
        val id: Int = view.id

        val listId = listOf(
            R.id.image_all,
            R.id.image_happy,
            R.id.image_sunny
        )

        if (id in listId) {
            handleFilter(id)
        } else if (id == R.id.button_new_phrase) {
            handleNewPhrase()
        } else if (view.id == R.id.text_user_name) {
            startActivity(Intent(this, UserActivity::class.java))
        }
    }

    /**
     * Função responsavel por exibir o nome do usuario
     */
    private fun showUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textUserName.text = "Olá, $name!"
    }

    private fun setListeners() {
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
        binding.textUserName.setOnClickListener(this)
    }

    /**
     * Função responsavel por filtrar as frases
     */
    private fun handleFilter(id: Int) {
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        when (id) {
            R.id.image_all -> {
                filter = MotivationConstants.PHRASEFILTER.ALL
                binding.imageAll.setColorFilter(
                    ContextCompat.getColor(this, R.color.white)
                )
            }
            R.id.image_happy -> {
                filter = MotivationConstants.PHRASEFILTER.HAPPY

                // Possível de trocar a fonte da imagem e atribuir ao elemento de layout
                // binding.imageHappy.setImageResource(R.drawable.ic_all)

                // Possível de trocar a cor do ícone
                binding.imageHappy.setColorFilter(
                    ContextCompat.getColor(this, R.color.white)
                )
            }
            else -> {
                filter = MotivationConstants.PHRASEFILTER.SUNNY
                binding.imageSunny.setColorFilter(
                    ContextCompat.getColor(this, R.color.white)
                )
            }
        }
    }

    /**
     * Função responsavel por mudar a frase
     */
    private fun handleNewPhrase() {
        val phrase = Mock().randomPhrase(filter)
        binding.textPhrase.text = phrase
    }

}
