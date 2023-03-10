package com.beatriz.convidados.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.beatriz.convidados.model.GuestModel
import com.beatriz.convidados.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GuestRepository.getInstance(application)

    fun insert(guest: GuestModel) {
        repository.insert(guest)
    }

}