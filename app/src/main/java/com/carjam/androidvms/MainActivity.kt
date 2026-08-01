package com.carjam.androidvms

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vmList = ListView(this)
        val vms = arrayOf(
            "Android-x86 7.1",
            "Android-x86 9",
            "Ubuntu VM",
            "Custom Linux VM"
        )

        vmList.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            vms
        )

        setContentView(vmList)
    }
}
