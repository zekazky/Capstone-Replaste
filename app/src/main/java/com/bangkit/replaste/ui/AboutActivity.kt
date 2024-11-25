package com.bangkit.replaste.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.bangkit.replaste.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.hide()
        setupBottomNavigation()

        findViewById<FloatingActionButton>(R.id.cameraButton)?.setOnClickListener {
            startActivity(Intent(this, CameraActivity::class.java))
        }

        findViewById<CardView>(R.id.versionCard).setOnClickListener {
            // Implementasi version card click
        }

        findViewById<CardView>(R.id.aboutUsCard).setOnClickListener {
            // Implementasi about us card click
        }
    }

    private fun setupBottomNavigation() {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_camera -> {
                    startActivity(Intent(this, CameraActivity::class.java))
                    true
                }
                R.id.nav_info -> {
                    true
                }
                else -> false
            }
        }
        bottomNav.selectedItemId = R.id.nav_info
    }
}