package com.astontraineeship.fragmentstraining

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.astontraineeship.fragmentstraining.task1.BackButtonClickListener
import com.astontraineeship.fragmentstraining.task1.FragmentA
import com.astontraineeship.fragmentstraining.task1.FragmentB
import com.astontraineeship.fragmentstraining.task1.FragmentC
import com.astontraineeship.fragmentstraining.task1.FragmentD
import com.astontraineeship.fragmentstraining.task1.FragmentTag
import com.astontraineeship.fragmentstraining.task1.NextButtonClickListener
import com.astontraineeship.fragmentstraining.task2.UsersFragment

class MainActivity : AppCompatActivity(), NextButtonClickListener, BackButtonClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(supportFragmentManager.beginTransaction()){
            add(R.id.container, MainFragment.newInstance())
            commit()
        }
    }

    override fun onNextButtonClicked(idButton: Int, bundle: Bundle?) {
        var fragment: Fragment? = null
        bundle?.let {
            fragment = convertButtonIdToNavFragmentWithBundle(idButton, bundle)
        }?:run {
            fragment = convertButtonIdToNavFragment(idButton)
        }
        fragment?.let {
            with(supportFragmentManager.beginTransaction()){
                addToBackStack((it as FragmentTag).getFragmentTag())
                replace(R.id.container, it)
                commit()
            }
        }

    }

    override fun onBackButtonClicked(idButton: Int) {
        val fragment = convertButtonIdToNavFragment(idButton)
        fragment?.let {
            supportFragmentManager.popBackStack((it as FragmentTag).getFragmentTag(), 0)
        }
    }

    private fun convertButtonIdToNavFragment(id: Int): Fragment? {
        var fragment : Fragment? = null
        when(id) {
            R.id.fragment_main_button_task_1 -> fragment = FragmentA.newInstance()
            R.id.fragment_main_button_task_2 -> fragment = UsersFragment.newInstance()

            R.id.fragment_a_button_to_b -> fragment = FragmentB.newInstance()
            R.id.fragment_b_button_to_c -> fragment = FragmentC()
            R.id.fragment_c_button_to_d -> fragment = FragmentD.newInstance()

            R.id.fragment_b_button_back_to_a -> fragment = FragmentA()
            R.id.fragment_c_button_back_to_a -> fragment = FragmentA()
            R.id.fragment_d_button_back_to_b -> fragment = FragmentB()
        }
        return fragment
    }

    private fun convertButtonIdToNavFragmentWithBundle(id: Int, bundle: Bundle): Fragment? {
        var fragment : Fragment? = null
        when(id) {
            R.id.fragment_b_button_to_c -> fragment = FragmentC.newInstance(bundle)
        }
        return  fragment
    }
}