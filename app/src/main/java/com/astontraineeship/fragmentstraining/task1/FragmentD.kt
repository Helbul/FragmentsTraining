package com.astontraineeship.fragmentstraining.task1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.astontraineeship.fragmentstraining.databinding.FragmentDBinding

class FragmentD : Fragment(), FragmentTag {
    private val binding get() = _binding!!
    private var _binding : FragmentDBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentDButtonBackToB.setOnClickListener {
            (requireActivity() as BackButtonClickListener).onBackButtonClicked(it.id)
        }
    }

    override fun getFragmentTag(): String {
        return FRAGMENT_D_TAG
    }

    companion object {
        const val FRAGMENT_D_TAG = "FRAGMENT_D_TAG"

        fun newInstance() = FragmentD()
    }
}