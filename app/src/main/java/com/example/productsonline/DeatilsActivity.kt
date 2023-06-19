package com.example.productsonline

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView

class DeatilsActivity : AppCompatActivity() {
    private var isLandscapeMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deatils)

        val productId = intent.getIntExtra("productId", 1)

        val fragmentB = ProductDetailsFragment()
        fragmentB.arguments = Bundle().apply {
            putInt("productId", productId)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.productDetailsFragmentContainer, fragmentB)
            .commit()
    }

    override fun onResume() {
        super.onResume()
        isLandscapeMode = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        if (isLandscapeMode) {
            finish()
        }
    }



}
