package com.beatriz.convidados.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beatriz.convidados.databinding.RowGuestBinding
import com.beatriz.convidados.model.GuestModel
import com.beatriz.convidados.view.listener.OnGuestListener
import com.beatriz.convidados.view.viewHolder.GuestsViewHolder

class GuestAdapter : RecyclerView.Adapter<GuestsViewHolder>() {

    private var guestList: List<GuestModel> = listOf()
    private lateinit var listener: OnGuestListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestsViewHolder {
        val item = RowGuestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestsViewHolder(item, listener)
    }

    override fun onBindViewHolder(holder: GuestsViewHolder, position: Int) {
        holder.bind(guestList[position])
    }

    override fun getItemCount(): Int {
        return guestList.count()
    }

    fun updatedGuests(list: List<GuestModel>) {
        guestList = list
        notifyDataSetChanged()
    }

    fun attachListener(guestListener: OnGuestListener){
        listener = guestListener
    }
}