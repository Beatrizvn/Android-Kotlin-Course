package com.beatriz.convidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beatriz.convidados.databinding.RowGuestBinding
import com.beatriz.convidados.model.GuestModel
import com.beatriz.convidados.view.listener.OnGuestListener
import com.beatriz.convidados.view.viewHolder.GuestsViewHolder

class GuestAdapter : RecyclerView.Adapter<GuestsViewHolder>() {
    // Lista de convidados
    private var guestList: List<GuestModel> = listOf()
    private lateinit var listener: OnGuestListener

    /**
     * Faz a criação do layout da linha
     * Faz a criação de várias linhas que vão mostrar cada um dos convidados
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestsViewHolder {
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestsViewHolder(item, listener)
    }
    /**
     * Para cada linha, este método é chamado
     * É responsável por atribuir os valores de cada item para uma linha específica
     */
    override fun onBindViewHolder(holder: GuestsViewHolder, position: Int) {
        holder.bind(guestList[position])
    }
    /**
     * Qual o tamanho da RecyclerView
     */
    override fun getItemCount(): Int {
        return guestList.count()
    }
    /**
     * Atualização da lista de convidados
     */
    fun updatedGuests(list: List<GuestModel>) {
        guestList = list
        notifyDataSetChanged()
    }
    /**
     * Eventos na listagem
     */
    fun attachListener(guestListener: OnGuestListener){
        listener = guestListener
    }
}