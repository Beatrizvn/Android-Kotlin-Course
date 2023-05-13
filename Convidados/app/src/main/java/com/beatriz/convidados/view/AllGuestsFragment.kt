package com.beatriz.convidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.beatriz.convidados.databinding.FragmentAllGuestsBinding
import com.beatriz.convidados.view.adapter.GuestAdapter
import com.beatriz.convidados.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!
    private val adapter = GuestAdapter()
    private lateinit var viewModel: AllGuestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val viewModel = ViewModelProvider(this).get(AllGuestsViewModel::class.java)
        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        // Layout -> sabe como vai ordenar e disponibilizar a listagem
        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)

        // Adapter -> faz a cola entre o layout e a sua lista
        binding.recyclerAllGuests.adapter = adapter

        viewModel.getAll()

        observe()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun observe() {
        viewModel.guests.observe(viewLifecycleOwner) {
            adapter.updatedGuests(it)
        }
    }
}