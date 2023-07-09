package com.test.nano_suite.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.nano_suite.R
import com.test.nano_suite.interfaces.Clicker
import com.test.nano_suite.models.products.ProductResponse

class ProductsAdapter(private val products: List<ProductResponse>, val clicker: Clicker) :
    RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView = itemView.findViewById(R.id.image)
        var price: TextView = itemView.findViewById(R.id.price)
        var name: TextView = itemView.findViewById(R.id.name)
        var description: TextView = itemView.findViewById(R.id.description)
        var ratings: RatingBar = itemView.findViewById(R.id.ratings)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val product = products[position]

        Glide.with(holder.itemView.context).load(product.image).into(holder.image)
        holder.price.text = "${product.price} AED"
        holder.name.text = product.title
        holder.description.text = product.description
        holder.ratings.rating = product.rating?.rate!!.toFloat()

        holder.itemView.setOnClickListener {
            clicker.onClick(position, product.id!!)
        }

    }

    override fun getItemCount(): Int {
        return products.size
    }

}