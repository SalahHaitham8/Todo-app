package com.example.todopractice1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todopractice1.databinding.FragmentSettingFragmentBinding


class setting_fragment : Fragment() {
lateinit var viewbinding:FragmentSettingFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewbinding=FragmentSettingFragmentBinding.inflate(layoutInflater,container,false)
        return viewbinding.root
    }


}