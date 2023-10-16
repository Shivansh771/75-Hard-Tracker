package com.example.hard75tracker.ui.streak

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hard75tracker.R
import com.example.hard75tracker.databinding.ActivityMainBinding
import com.example.hard75tracker.databinding.FragmentDashboardBinding

class StreakFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private lateinit var sharedPref:SharedPreferences
    val imageList= ArrayList<String>()

    private lateinit var streak:String
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val streakViewModel =
                ViewModelProvider(this).get(StreakViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        sharedPref = PreferenceManager.getDefaultSharedPreferences(context)

        streak=sharedPref.getString("streak","").toString()
        var streak1=streak.toInt()

        for(i in 1..streak1){
            var st= "s${i-1}"
            imageList.add(st)

        }
        Toast.makeText(requireContext(),"$imageList",Toast.LENGTH_SHORT).show()

        streakViewModel.text.observe(viewLifecycleOwner) {
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        for(id in imageList){
            val imageViewField = FragmentDashboardBinding::class.java.getDeclaredField(id)
            val imageView = imageViewField.get(binding) as ImageView

            imageView.setImageResource(R.drawable.streak2)


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}