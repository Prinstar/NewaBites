package com.example.newabites

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartActivity : AppCompatActivity() {

    private lateinit var totalText: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var clearBtn: Button
    private lateinit var checkoutBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        supportActionBar?.title = "Your Cart"

        // Connect Views
        recyclerView = findViewById(R.id.cartRecyclerView)
        totalText = findViewById(R.id.totalText)
        clearBtn = findViewById(R.id.clearCartBtn)
        checkoutBtn = findViewById(R.id.checkoutBtn)

        // Setup RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter =
            CartAdapter(FoodAdapter.cartList) { updateTotal() }

        // Clear Cart Button
        clearBtn.setOnClickListener {
            FoodAdapter.cartList.clear()
            recyclerView.adapter?.notifyDataSetChanged()
            updateTotal()
        }

        // Checkout Button
        checkoutBtn.setOnClickListener {
            totalText.text = "Order Placed! 🎉"
        }

        updateTotal()
    }

    private fun updateTotal() {
        var total = 0.0
        for (item in FoodAdapter.cartList) {
            total += item.foodItem.price * item.quantity
        }
        totalText.text = "Total: $${"%.2f".format(total)}"
    }
}