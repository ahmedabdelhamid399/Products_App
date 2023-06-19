package com.example.productsonline

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide


class ProductDetailsFragment : Fragment() {
    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var priceTextView: TextView
    private lateinit var ratingBar: RatingBar
    private lateinit var brandTextView: TextView
    private lateinit var thumbnailImageView: ImageView



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_product_details, container, false)
        titleTextView = view.findViewById(R.id.titleTv)
        descriptionTextView = view.findViewById(R.id.descriptionTv)
        //priceTextView = view.findViewById(R.id.priceTv)
        ratingBar = view.findViewById(R.id.ratingBar)
        brandTextView = view.findViewById(R.id.brandTv)
        thumbnailImageView = view.findViewById(R.id.imageView)

        val productId = arguments?.getInt("productId") ?: 1
        updateProduct(productId)

        return view
    }

    fun updateProduct(productId: Int) {
        val product = ProductUtil.getProducts().first { it.id == productId }
        titleTextView.text = product.title
        descriptionTextView.text = product.description
        //priceTextView.text = product.price.toString()
        ratingBar.rating = product.rating.toFloat()
        brandTextView.text = product.brand

        Glide.with(this)
            .load(product.thumbnail)
            .into(thumbnailImageView)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (activity is DeatilsActivity) {
                activity?.finish()
            }
        }
    }


}