package com.carjam.androidvms

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val pickFileCode = 100
    private val vms = mutableListOf("Android-x86 7.1", "Android-x86 9", "Ubuntu VM", "Custom Linux VM")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showHome()
    }

    private fun showHome() {
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        val vmList = ListView(this)
        vmList.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, vms)

        val addButton = Button(this)
        addButton.text = "Create New VM"
        addButton.setOnClickListener { showWizard() }

        val isoButton = Button(this)
        isoButton.text = "Select ISO / ROM"
        isoButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.type = "*/*"
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            startActivityForResult(intent, pickFileCode)
        }

        layout.addView(vmList)
        layout.addView(addButton)
        layout.addView(isoButton)
        setContentView(layout)
    }

    private fun showWizard() {
        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        val nameInput = EditText(this)
        nameInput.hint = "VM Name"

        val osInput = EditText(this)
        osInput.hint = "OS Type (Android/Linux)"

        val createButton = Button(this)
        createButton.text = "Create VM"
        createButton.setOnClickListener {
            val name = nameInput.text.toString().ifEmpty { "New VM" }
            val os = osInput.text.toString().ifEmpty { "Unknown OS" }
            vms.add("$name ($os)")
            Toast.makeText(this, "VM Created", Toast.LENGTH_SHORT).show()
            showHome()
        }

        layout.addView(nameInput)
        layout.addView(osInput)
        layout.addView(createButton)
        setContentView(layout)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pickFileCode && resultCode == Activity.RESULT_OK) {
            Toast.makeText(this, "ISO/ROM Selected: ${data?.data}", Toast.LENGTH_LONG).show()
        }
    }
}
