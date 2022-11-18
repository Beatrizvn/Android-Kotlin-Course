package com.beatriz.motivation.ui

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
    private var categoryId = MotivationConstants.PHRASEFILTER.ALL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Esconder a barra
        supportActionBar?.hide()
        handleFilter(R.id.image_all)
        handleNewPhrase()

        //Pegando o nome digitado
        handleUserName()

        //Eventos
        binding.buttonNewPhrase.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
    }

    /**
     * Função responsavel pelas ações de clique
     */
    override fun onClick(view: View) {
        if (view.id == R.id.button_new_phrase) {
            handleNewPhrase()
        } else if (view.id in listOf(R.id.image_sunny, R.id.image_all, R.id.image_happy)) {
            handleFilter(view.id)
        }
    }

    /**
     * Função responsavel por exibir o nome do usuario
     */
    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textUserName.text = "Olá, $name!"
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
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.PHRASEFILTER.ALL
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.PHRASEFILTER.SUNNY
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.PHRASEFILTER.HAPPY
            }
        }
    }

    /**
     * Função responsavel por mudar a frase
     */
    private fun handleNewPhrase() {
        val phrase = Mock().randomPhrase(categoryId)
        binding.textPhrase.text = phrase
    }

}
