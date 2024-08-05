package com.example.sun.screen

import com.example.sun.R
import com.example.sun.screen.home.HomeFragment
import com.example.sun.utils.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(HomeFragment::javaClass.name)
            .replace(R.id.layoutContainer, HomeFragment())
            .commit()
    }
}
