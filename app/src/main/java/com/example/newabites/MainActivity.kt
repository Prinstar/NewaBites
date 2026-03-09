package com.example.newabites

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewCartBtn: Button
    private val foodList = ArrayList<FoodItem>()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_cart -> {
                startActivity(Intent(this, CartActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Menu"

        recyclerView = findViewById(R.id.recyclerView)
        viewCartBtn = findViewById(R.id.viewCartBtn)

        foodList.add(FoodItem("Burger", "Cheesy Beef Burger", 5.99, R.drawable.burger))
        foodList.add(FoodItem("Pizza", "Pepperoni Slice", 3.99, R.drawable.pizza))
        foodList.add(FoodItem("Fries", "Crispy Fries", 2.49, R.drawable.fries))
        foodList.add(FoodItem("Wings", "Spicy Chicken Wings", 6.49, R.drawable.wings))
        foodList.add(FoodItem("Pasta", "Tomato Meatball Pasta", 7.99, R.drawable.pasta))

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = FoodAdapter(this, foodList)

        viewCartBtn.setOnClickListener {
            startActivity(Intent(this, CartActivity::class.java))
        }
    }
}