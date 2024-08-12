package com.example.sun.screen.search

import android.util.Log
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sun.R
import com.example.sun.databinding.FragmentSearchBinding
import com.example.sun.screen.detail.AppBarFragment
import com.example.sun.screen.detail.DetailFragment
import com.example.sun.utils.base.BaseFragment

class SearchFragment : BaseFragment<FragmentSearchBinding>() {
    override fun inflateViewBinding(inflater: LayoutInflater): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater)
    }

    override fun initData() {
    }

    override fun initView() {
        childFragmentManager.beginTransaction()
            .replace(R.id.searchFragment, DetailSearchFragment())
            .commit()

    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}
