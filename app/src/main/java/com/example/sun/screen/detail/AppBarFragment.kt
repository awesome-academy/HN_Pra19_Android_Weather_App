package com.example.sun.screen.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.example.sun.databinding.FragmentAppBarBinding
import com.example.sun.utils.base.BaseFragment

class AppBarFragment : BaseFragment<FragmentAppBarBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater): FragmentAppBarBinding {
        return FragmentAppBarBinding.inflate(inflater)
    }
    override fun initData() {
    }
    override fun initView() {
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.btnBack.setOnClickListener {
            Log.i("onViewCreated", "btnBack")
            onBtnBackClicked()
        }
    }
    private fun onBtnBackClicked() {
        Log.i("AppBarFragment", "parentFragmentManager hashCode: ${parentFragmentManager.hashCode()}")
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.popBackStack()
        Log.i("onBtnBackClicked", "Back stack entry count: ${parentFragmentManager.backStackEntryCount}")
    }
}
