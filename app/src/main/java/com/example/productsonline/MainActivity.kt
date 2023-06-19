package com.example.productsonline

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), Communicator{
    private var isLandscapeMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        isLandscapeMode = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        if (isLandscapeMode) {

            val fragmentA = ProductsListFragment()
            val fragmentB = ProductDetailsFragment()

            supportFragmentManager.beginTransaction()
                .replace(R.id.productsListFragmentContainer, fragmentA)
                .replace(R.id.productDetailsFragmentContainer, fragmentB)
                .commit()
        } else {

            val fragmentA = ProductsListFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.productsListFragmentContainer, fragmentA)
                .commit()
        }
    }

    override fun onProductClicked(product: Product) {
        if (isLandscapeMode) {
            val fragmentB = supportFragmentManager.findFragmentById(R.id.productDetailsFragmentContainer) as? ProductDetailsFragment
            fragmentB?.updateProduct(product.id)

        } else {

            Intent (this, DeatilsActivity::class.java).also {
                it.putExtra("productId", product.id)
                startActivity(it)
            }

        }
    }
}