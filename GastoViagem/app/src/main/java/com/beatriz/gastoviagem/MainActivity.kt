package com.beatriz.gastoviagem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.beatriz.gastoviagem.databinding.ActivityMainBinding

/**
 * Função responsálvel por fazer a criação do Activity
 */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    //"expande" para conseguir pegar os elementos da interface
    //binding -> a ligação, a amarração, que liga
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Faz com que o ViewBinding identifique os elementos e mapeie para 'binding'
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Faz a atribuição do layout
        setContentView(binding.root)

        //adiciona evento ao elemento da interface
        binding.buttonCalculate.setOnClickListener(this)
    }

    /**
     * Função responsálvel por tratar eventos de click
     */
    override fun onClick(view: View) {
        if (view.id == R.id.button_calculate) {
            calculate()
        }
    }

    /**
     * Função responsálvel por validar os valores inseridos nas EditText, para o calculate()
     */
    private fun isValid(): Boolean {
        return (binding.editDistance.text.toString() != ""
                && binding.editPrice.text.toString() != ""
                && binding.editAutonomy.text.toString() != ""
                && binding.editAutonomy.text.toString().toFloat() != 0f)
    }

    /**
     * Função responsálvel por calcular o gasto da viagem
     */
    private fun calculate() {

        if (isValid()) {

            val distance = binding.editDistance.text.toString().toFloat()
            val price = binding.editPrice.text.toString().toFloat()
            val autonomy = binding.editAutonomy.text.toString().toFloat()

            val totalValue = (distance * price) / autonomy

            val totalValueStr = "R$ ${"%.2f".format(totalValue)}"
            binding.textTotalValue.text = totalValueStr

        } else Toast.makeText(this, R.string.validation_fill_all_fields, Toast.LENGTH_SHORT).show()
    }

}