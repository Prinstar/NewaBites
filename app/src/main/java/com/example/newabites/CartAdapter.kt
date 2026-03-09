package com.example.newabites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private val cartList: ArrayList<CartItem>,
    private val onCartUpdated: () -> Unit
) : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.cartImage)
        val name: TextView = view.findViewById(R.id.cartName)
        val price: TextView = view.findViewById(R.id.cartPrice)
        val quantity: TextView = view.findViewById(R.id.quantityText)
        val plus: Button = view.findViewById(R.id.plusBtn)
        val minus: Button = view.findViewById(R.id.minusBtn)
        val remove: Button = view.findViewById(R.id.removeBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = cartList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cartItem = cartList[position]

        holder.image.setImageResource(cartItem.foodItem.image)
        holder.name.text = cartItem.foodItem.name
        holder.quantity.text = cartItem.quantity.toString()

        val itemTotal = cartItem.foodItem.price * cartItem.quantity
        holder.price.text = "$${"%.2f".format(itemTotal)}"

        holder.plus.setOnClickListener {
            cartItem.quantity++
            notifyItemChanged(position)
            onCartUpdated()
        }

        holder.minus.setOnClickListener {
            if (cartItem.quantity > 1) {
                cartItem.quantity--
                notifyItemChanged(position)
            }
            onCartUpdated()
        }

        holder.remove.setOnClickListener {
            cartList.removeAt(position)
            notifyItemRemoved(position)
            onCartUpdated()
        }
    }
}