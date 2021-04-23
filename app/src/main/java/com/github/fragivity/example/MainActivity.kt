package com.github.fragivity.example

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.proxyFragmentFactory
import androidx.navigation.fragment.NavHostFragment
import com.github.fragivity.composable
import com.github.fragivity.debug.showDebugView
import com.github.fragivity.deeplink.handleDeepLink
import com.github.fragivity.loadRoot
import com.github.fragivity.stringArgument


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        proxyFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FragmentManager.enableDebugLogging(true)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host) as NavHostFragment

        navHostFragment.loadRoot(HomeFragment::class)
//        //you also can loadRoot with a factory
//        navHostFragment.loadRoot { HomeFragment() }

        navHostFragment.handleDeepLink(intent)
        navHostFragment.showDebugView(findViewById<ViewGroup>(R.id.container))

        with(navHostFragment) {
            composable("feed") { FeedFragment.newInstance() }
            composable("search?keyword={keyword}", stringArgument("keyword")) {
                SearchFragment.newInstance()
            }
        }
    }
}
