package com.example.newabites

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class FoodAdapter(
    private val context: Context,
    private val foodList: ArrayList<FoodItem>
) : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    companion object {
        val cartList = ArrayList<CartItem>()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.foodImage)
        val name: TextView = itemView.findViewById(R.id.foodName)
        val description: TextView = itemView.findViewById(R.id.foodDescription)
        val price: TextView = itemView.findViewById(R.id.foodPrice)
        val button: Button = itemView.findViewById(R.id.addToCartBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.food_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = foodList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = foodList[position]

        holder.name.text = item.name
        holder.description.text = item.description
        holder.price.text = "$${item.price}"
        holder.image.setImageResource(item.image)

        holder.button.setOnClickListener {
            val existingItem = cartList.find { it.foodItem.name == item.name }

            if (existingItem != null) {
                existingItem.quantity++
            } else {
                cartList.add(CartItem(item, 1))
            }

            Toast.makeText(context, "Added to Cart", Toast.LENGTH_SHORT).show()
            Toast.makeText(context, "Added to Cart", Toast.LENGTH_SHORT).show()
        }
    }
}