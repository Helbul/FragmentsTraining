package com.astontraineeship.fragmentstraining.task2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.api.load
import com.astontraineeship.fragmentstraining.databinding.FragmentUserDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class UserDetailFragment : BottomSheetDialogFragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentUserDetailBinding? = null
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            user = it.getParcelable(ARG_USER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { it ->
            user = it.getParcelable(ARG_USER)
            user?.let {user ->  showUser(user)}
        }

        with(binding) {
            buttonSave.setOnClickListener {
                val firstName = inputFirstname.text.toString()
                val lastName = inputLastname.text.toString()
                val photoUrl = inputUrlPhoto.text.toString()
                val phoneNumber = inputNumber.text.toString()
                if (user == null) dismiss()
                user = user?.let {
                            it1 -> User(it1.userId, firstName, lastName, phoneNumber, photoUrl)
                    }
                saveUser(user!!)
            }

            inputUrlPhoto.setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    val url = inputUrlPhoto.text.toString()
                    photo.load(url) {build()}
                }
            }
        }
    }

    private fun showUser(user: User) {
        with(binding) {
            photo.load(user.photoUrl){build()}
            inputUrlPhoto.setText(user.photoUrl)
            inputFirstname.setText(user.firstName)
            inputLastname.setText(user.lastName)
            inputNumber.setText(user.phoneNumber)
        }
    }

    private fun saveUser(user: User) {
        val bundle = Bundle().apply {
            putParcelable(ARG_USER, user)
        }
        parentFragmentManager.setFragmentResult(KEY_RESULT_USER, bundle)
        dismiss()
    }

    companion object {
        const val USER_DETAIL_FRAGMENT_TAG = "USER_DETAIL_FRAGMENT_TAG"
        const val ARG_USER = "ARG_USER"
        const val KEY_RESULT_USER = "KEY_RESULT_USER"


        fun newInstance(user: User): UserDetailFragment =
            UserDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_USER, user)
                }
            }
    }
}