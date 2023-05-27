package com.beatriz.convidados.repository

import android.content.ContentValues
import android.content.Context
import com.beatriz.convidados.constants.DataBaseConstants
import com.beatriz.convidados.model.GuestModel


class GuestRepository(context: Context) {

    // Acesso ao banco de dados
    private val dataBase = GuestDataBase.getDatabase(context).guestDAO()

    fun insert(guest: GuestModel): Boolean {
        return dataBase.insert(guest) > 0
    }

    fun update(guest: GuestModel): Boolean {
        return dataBase.update(guest) > 0
    }

    // poderia passar um GuestModel como parametro ao inves de Int
    fun delete(guestID: Int) {
        val guest = get(guestID)
        return dataBase.delete(guest)
    }

    fun get(id: Int): GuestModel {
        return dataBase.get(id)
    }

    fun getAll(): List<GuestModel> {
        return dataBase.getAll()
    }

    fun getPresent(): List<GuestModel> {
        return dataBase.getPresent()
    }

    fun getAbsent(): List<GuestModel> {
        return dataBase.getAbsent()
    }

}
