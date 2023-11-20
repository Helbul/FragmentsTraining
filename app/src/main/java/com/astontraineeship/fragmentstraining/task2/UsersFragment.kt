package com.astontraineeship.fragmentstraining.task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.astontraineeship.fragmentstraining.databinding.FragmentUsersBinding
import com.astontraineeship.fragmentstraining.task1.FragmentTag

class UsersFragment : Fragment(), FragmentTag {
    private val binding get() = _binding!!
    private var _binding : FragmentUsersBinding? = null
    private val users: MutableList<User> = mutableListOf<User>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUsersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        for (i in 1..20){
            users.add(CreateNewUser.getNextUser())
        }

        val recyclerView = binding.usersRecyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)
        val adapter = UsersAdapter(object : OnListItemClickListener {
            override fun onItemClick(user: User) {
                UserDetailFragment
                    .newInstance(user)
                    .show(parentFragmentManager, UserDetailFragment.USER_DETAIL_FRAGMENT_TAG)
            }
        })
        recyclerView.adapter = adapter
        adapter.setUsers(users)

        parentFragmentManager.setFragmentResultListener(
            UserDetailFragment.KEY_RESULT_USER,
            viewLifecycleOwner
        ) {_, result ->
            val user: User? = result.getParcelable(UserDetailFragment.ARG_USER)
            user?.let {user ->
                val position = users.indexOfFirst { it.userId ==  user.userId}
                if (position != -1) {
                    adapter.replaceUser(user, position)
                }
            }
        }
    }

    override fun onStop() {
        super.onStop()
        CreateNewId.clearId()
    }

    override fun getFragmentTag(): String = USERS_FRAGMENT_TAG

    companion object {
        const val USERS_FRAGMENT_TAG = "USERS_FRAGMENT_TAG"

        fun newInstance() = UsersFragment()
    }
}