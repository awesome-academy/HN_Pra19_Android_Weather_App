package com.example.sun.screen

import android.util.Log
import com.example.sun.R
import com.example.sun.screen.home.HomeFragment
import com.example.sun.utils.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        Log.i("MainActivity", "hashCode: ${supportFragmentManager.hashCode()}")
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.layoutContainer, HomeFragment.newInstance())
            .addToBackStack(HomeFragment::class.java.name)
            .commit()
        supportFragmentManager.executePendingTransactions()
        Log.i("MainActivity", "Back stack entry count after: ${supportFragmentManager.backStackEntryCount}")
    }
}
