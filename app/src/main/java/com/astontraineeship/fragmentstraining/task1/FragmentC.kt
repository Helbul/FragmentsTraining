package com.astontraineeship.fragmentstraining.task1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.astontraineeship.fragmentstraining.databinding.FragmentCBinding

class FragmentC : Fragment(), FragmentTag {
    private val binding get() = _binding!!
    private var _binding: FragmentCBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val arguments = arguments
        if (arguments != null) {
            val text = arguments.getString(FRAGMENT_C_TEXT_EXTRA)
            binding.fragmentCTextFromB.text = text
        }

        binding.fragmentCButtonToD.setOnClickListener {
            (requireActivity() as NextButtonClickListener).onNextButtonClicked(it.id, null)
        }

        binding.fragmentCButtonBackToA.setOnClickListener {
            (requireActivity() as BackButtonClickListener).onBackButtonClicked(it.id)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun getFragmentTag(): String {
        return FRAGMENT_C_TAG
    }

    companion object {
        const val FRAGMENT_C_TAG = "FRAGMENT_C_TAG"

        const val FRAGMENT_C_TEXT_EXTRA = "FRAGMENT_C_TEXT_EXTRA"


        fun newInstance(bundle: Bundle) = FragmentC().apply {
            arguments = bundle
        }
    }
}