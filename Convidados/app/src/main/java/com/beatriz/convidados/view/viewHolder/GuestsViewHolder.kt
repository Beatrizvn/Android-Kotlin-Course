package com.beatriz.convidados.view.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.beatriz.convidados.databinding.RowGuestBinding
import com.beatriz.convidados.model.GuestModel
import com.beatriz.convidados.view.listener.OnGuestListener

class GuestsViewHolder(private val bind: RowGuestBinding, private val listener: OnGuestListener) :
    RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel) {
        bind.textName.text = guest.name

        bind.textName.setOnClickListener{
            listener.onClick(guest.id)
        }
    }

}