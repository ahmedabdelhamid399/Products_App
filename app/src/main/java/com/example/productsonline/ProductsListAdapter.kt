package com.example.productsonline

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductsListAdapter(var myListner:(Product)->Unit) :
    ListAdapter<Product, ProductsListAdapter.ProductViewHolder>(ProductDiffUtil()) {


    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title by lazy { itemView.findViewById<TextView>(R.id.titleTv) }
        private val brand by lazy { itemView.findViewById<TextView>(R.id.brandTv) }
        private val thumbnail by lazy { itemView.findViewById<ImageView>(R.id.imageView) }
        private val layout by lazy { itemView.findViewById<ConstraintLayout>(R.id.constraintlayout) }

        fun bind(product: Product) {
            title.text = product.title
            brand.text = product.brand
            Glide.with(itemView.context).load(product.thumbnail).into(thumbnail)

            layout.setOnClickListener {
                myListner(product)
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}