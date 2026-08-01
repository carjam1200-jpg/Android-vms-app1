package com.carjam.androidvms

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val pickFileCode = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

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

        val pickerButton = Button(this)
        pickerButton.text = "Select ISO / ROM"
        pickerButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "*/*"
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(intent, pickFileCode)
        }

        layout.addView(vmList)
        layout.addView(pickerButton)

        setContentView(layout)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == pickFileCode && resultCode == Activity.RESULT_OK) {
            val file = data?.data
            Toast.makeText(this, "Selected: $file", Toast.LENGTH_LONG).show()
        }
    }
}
