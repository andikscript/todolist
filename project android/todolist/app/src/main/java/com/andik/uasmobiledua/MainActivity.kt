package com.andik.uasmobiledua

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.andik.uasmobiledua.R
import com.andik.uasmobiledua.ui.fragment.TodoFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var menu : Menu
    private lateinit var menuitem : MenuItem
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpBottomNav()
        callFragment(0, TodoFragment())
    }

    private fun setUpBottomNav() {
        bottomNavigationView = findViewById(R.id.nav_view)
        menu = bottomNavigationView.menu
        menuitem = menu.getItem(0)
        menuitem.isChecked = true

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.navigation_home -> {
                    callFragment(0, TodoFragment())
                }
            }
            false
        }
    }

    private fun callFragment(int: Int, fragment: Fragment) {
        menuitem = menu.getItem(int)
        menuitem.isChecked = true
        callFragment(fragment)
    }

    private fun callFragment(f: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, f)
            .commit()
    }
}