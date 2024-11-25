package com.bangkit.replaste.ui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bangkit.replaste.R
import com.bangkit.replaste.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        inisialisasiUI()
        setupNavigasiBawah()

        binding.cameraButton.setOnClickListener {
            if (checkCameraPermission()) {
                openCamera()
            }
        }
    }

    private fun inisialisasiUI() {
        // Fitur cards
        val cards = listOf(
            binding.featureGrid.getChildAt(0) as CardView,
            binding.featureGrid.getChildAt(1) as CardView,
            binding.featureGrid.getChildAt(2) as CardView
        )

        cards[0].setOnClickListener {
            Toast.makeText(this, "Histori", Toast.LENGTH_SHORT).show()
        }

        cards[1].setOnClickListener {
            Toast.makeText(this, "Jenis Plastik", Toast.LENGTH_SHORT).show()
        }

        cards[2].setOnClickListener {
            Toast.makeText(this, "Lokasi", Toast.LENGTH_SHORT).show()
        }

        // Preview sections
        val preview1 = binding.previewSection.getChildAt(0) as CardView
        val preview2 = binding.previewSection.getChildAt(1) as CardView

        preview1.setOnClickListener {
            Toast.makeText(this, "Preview Jenis Plastik 1", Toast.LENGTH_SHORT).show()
        }

        preview2.setOnClickListener {
            Toast.makeText(this, "Preview Jenis Plastik 2", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupNavigasiBawah() {
        binding.bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    true
                }
                R.id.nav_camera -> {
                    if (checkCameraPermission()) {
                        openCamera()
                    }
                    true
                }
                R.id.nav_info -> {
                    startActivity(Intent(this, AboutActivity::class.java))
                    true
                }
                else -> false
            }
        }
        binding.bottomNav.selectedItemId = R.id.nav_home
    }

    private fun checkCameraPermission(): Boolean {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                CAMERA_REQUEST_CODE
            )
            return false
        }
        return true
    }

    private fun openCamera() {
        val intent = Intent(this, CameraActivity::class.java)
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openCamera()
                } else {
                    Toast.makeText(
                        this,
                        "Izin kamera diperlukan untuk fitur ini",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            val photoUri = data?.getStringExtra("photo_uri")
            Toast.makeText(this, "Foto berhasil diambil", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onBackPressed() {
        if (isTaskRoot) {
            showExitDialog()
        } else {
            super.onBackPressed()
        }
    }

    private fun showExitDialog() {
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Keluar Aplikasi")
            .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
            .setPositiveButton("Ya") { _, _ ->
                finish()
            }
            .setNegativeButton("Tidak", null)
            .show()
    }

    companion object {
        private const val CAMERA_REQUEST_CODE = 1001
        private const val CAMERA_PERMISSION_CODE = 100
    }
}