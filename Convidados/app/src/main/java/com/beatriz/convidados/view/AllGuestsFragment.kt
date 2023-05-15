package com.beatriz.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.beatriz.convidados.constants.DataBaseConstants
import com.beatriz.convidados.databinding.FragmentAllGuestsBinding
import com.beatriz.convidados.view.adapter.GuestAdapter
import com.beatriz.convidados.view.listener.OnGuestListener
import com.beatriz.convidados.viewmodel.GuestsViewModel

class AllGuestsFragment : Fragment() {

    private var _binding: FragmentAllGuestsBinding? = null
    private val binding get() = _binding!!
    private val adapter = GuestAdapter()
    private lateinit var viewModel: GuestsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewModel = ViewModelProvider(this)[GuestsViewModel::class.java]
        _binding = FragmentAllGuestsBinding.inflate(inflater, container, false)

        /*
        Layout -> sabe como vai ordenar e disponibilizar a listagem
        Atribui um layout que diz como a RecyclerView se comporta
        */
        binding.recyclerAllGuests.layoutManager = LinearLayoutManager(context)

        /*
        Adapter -> faz a cola entre o layout e a sua lista
        Faz a ligação da RecyclerView com a listagem de itens
         */
        binding.recyclerAllGuests.adapter = adapter

        val listener = object : OnGuestListener {
            override fun onClick(id: Int) {
                val intent = Intent(context, GuestFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(DataBaseConstants.GUEST.ID, id)
                intent.putExtras(bundle)

                startActivity(intent)

            }

            override fun onDelete(id: Int) {
                viewModel.delete(id)
                viewModel.getAll()
            }
        }

        adapter.attachListener(listener)

        observe()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
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