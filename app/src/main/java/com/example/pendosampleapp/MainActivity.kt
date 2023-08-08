package com.example.pendosampleapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import sdk.pendo.io.Pendo
import sdk.pendo.io.PendoPhasesCallbackInterface
import java.lang.NullPointerException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPendo(this, "sample-pendo")
        setContentView(R.layout.activity_main)

        val listView: ListView = findViewById(R.id.listView)
        val items = arrayOf("Item 1", "Item 2")

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = items[position]
            if (true) {
                throw NullPointerException()
            }
            Toast.makeText(this, "Clicked: $selectedItem", Toast.LENGTH_SHORT).show()
        }
    }
}

fun initPendo(context: Context, visitorID: String?) {
    val pendoAppKey = "some-valid-key-here"
    Pendo.setup(
        context,
        pendoAppKey,
        Pendo.PendoOptions(),
        object : PendoPhasesCallbackInterface {
            override fun onInitStarted() {
                Log.d("pendo", "Pendo init started")
            }

            override fun onInitComplete() {
                Log.d("pendo", "Pendo init complete")
            }

            override fun onInitFailed() {
                Log.d("pendo", "Pendo init failed")
            }
        })

    Pendo.startSession(visitorID, null, null, null)
}
