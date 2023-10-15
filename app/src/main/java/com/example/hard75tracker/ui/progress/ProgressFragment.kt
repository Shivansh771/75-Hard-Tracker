package com.example.hard75tracker.ui.progress

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hard75tracker.adapter.ProgressAdapter
import com.example.hard75tracker.databinding.FragmentNotificationsBinding
import java.io.File

class ProgressFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val progressViewModel =
                ViewModelProvider(this).get(ProgressViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root


        progressViewModel.text.observe(viewLifecycleOwner) {
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView= _binding?.rvProgress
        if (recyclerView != null) {
            recyclerView.layoutManager=LinearLayoutManager(requireContext())
        }
        val directory= File("/storage/emulated/0/Android/data/com.example.hard75Tracker/files/75HardImages")
        val files=directory.listFiles {file->file.extension in listOf("jpg","png") }
        val imageList=files?.map{it.absolutePath}
        if (recyclerView != null) {
            recyclerView.adapter= imageList?.let { ProgressAdapter(it) }
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}