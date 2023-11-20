package com.astontraineeship.fragmentstraining.task1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.astontraineeship.fragmentstraining.databinding.FragmentABinding

class FragmentA : Fragment(), FragmentTag {
    private val binding get() = _binding!!
    private var _binding: FragmentABinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentABinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentAButtonToB.setOnClickListener {
            (requireActivity() as NextButtonClickListener).onNextButtonClicked(it.id, null)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun getFragmentTag(): String {
        return FRAGMENT_A_TAG
    }

    companion object {
        const val FRAGMENT_A_TAG = "FRAGMENT_A_TAG"

        fun newInstance() = FragmentA()
    }
}