package com.beatriz.convidados.view.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.beatriz.convidados.databinding.RowGuestBinding
import com.beatriz.convidados.model.GuestModel

class GuestsViewHolder(private val bind: RowGuestBinding) : RecyclerView.ViewHolder(bind.root) {

    fun bind(guest: GuestModel) {
        bind.textName.text = guest.name
    }

}