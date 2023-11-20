package com.astontraineeship.fragmentstraining.task1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.astontraineeship.fragmentstraining.databinding.FragmentBBinding

class FragmentB : Fragment(), FragmentTag {
    private val textResult = "Hello Fragment C"
    private val bundle = Bundle()
    private val binding get() = _binding!!
    private var _binding : FragmentBBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bundle.putString(FragmentC.FRAGMENT_C_TEXT_EXTRA, textResult)

        binding.fragmentBButtonToC.setOnClickListener {
            (requireActivity() as NextButtonClickListener).onNextButtonClicked(it.id, bundle)
        }

        binding.fragmentBButtonBackToA.setOnClickListener {
            (requireActivity() as BackButtonClickListener).onBackButtonClicked(it.id)
        }
    }

//    override fun getFragmentBundleResult(): Bundle {
//        return bundle
//    }

    override fun getFragmentTag(): String {
        return FRAGMENT_B_TAG
    }

    companion object {

        const val FRAGMENT_B_TAG = "FRAGMENT_B_TAG"
        fun newInstance() = FragmentB()
    }
}