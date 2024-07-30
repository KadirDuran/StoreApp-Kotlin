package com.example.storeapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapp.databinding.ProductItemBinding
import com.squareup.picasso.Picasso
import java.io.Serializable
import java.net.URL
import java.net.URLDecoder
import java.net.URLEncoder

class ProductAdapter(val ProductList: ArrayList<Product>) :  RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    class ProductViewHolder(val binding : ProductItemBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val bindings = ProductItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(bindings)
    }

    override fun getItemCount(): Int {
        return  ProductList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.binding.txtProductName.text = ProductList[position].title
        holder.binding.txtProductPrice.text = ProductList[position].price.toString() + "$"
        Picasso.get().load(ProductList[position].imgUrl.toString()).into(holder.binding.productImg)



        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context,OneProduct :: class.java)
            intent.putExtra("selectedItem",ProductList[position])
            //Product Nesnesi "Serializable" ozelligini almalı!
            holder.itemView.context.startActivity(intent)


        }
    }


}