package com.beatriz.convidados.view.viewHolder

import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.beatriz.convidados.databinding.RowGuestBinding
import com.beatriz.convidados.model.GuestModel
import com.beatriz.convidados.view.listener.OnGuestListener

class GuestsViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) :

    RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel) {
        // Atribui valores
        bind.textName.text = guest.name

        // Atribui eventos
        bind.textName.setOnClickListener {
            listener.onClick(guest.id)
        }

        // Atribui eventos
        bind.textName.setOnLongClickListener {

            AlertDialog.Builder(itemView.context)
                .setTitle("Remoção de Convidado")
                .setMessage("Tem certeza que deseja remover?")
                .setPositiveButton("Sim") { dialog, which -> listener.onDelete(guest.id) }
                .setNegativeButton("Não", null)
                .create()
                .show()

            true
        }
    }

}