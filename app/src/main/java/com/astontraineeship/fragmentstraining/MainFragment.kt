package com.astontraineeship.fragmentstraining

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.astontraineeship.fragmentstraining.databinding.FragmentMainBinding
import com.astontraineeship.fragmentstraining.task1.NextButtonClickListener


class MainFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentMainBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            fragmentMainButtonTask1.setOnClickListener {
                (requireActivity() as NextButtonClickListener).onNextButtonClicked(it.id, null)
            }

            fragmentMainButtonTask2.setOnClickListener {
                (requireActivity() as NextButtonClickListener).onNextButtonClicked(it.id, null)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}