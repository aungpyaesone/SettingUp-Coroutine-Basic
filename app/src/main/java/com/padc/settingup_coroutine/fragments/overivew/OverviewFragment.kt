package com.padc.settingup_coroutine.fragments.overivew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.padc.settingup_coroutine.adapters.PhotoGridAdapter
import com.padc.settingup_coroutine.databinding.FragmentOverviewBinding

class OverviewFragment : Fragment(){
    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val binding = FragmentOverviewBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.photosGrid.adapter = PhotoGridAdapter()
        return binding.root
    }

}