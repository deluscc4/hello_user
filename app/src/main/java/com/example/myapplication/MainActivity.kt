package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var result: ActivityResultLauncher<Intent>
    var name = ""
    var old_name = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonChangeName.setOnClickListener {
            val i = Intent(this, MainActivity2::class.java)
            i.putExtra("name", name)
            result.launch(i)
        }

        result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.data != null && it.resultCode == 1) {
                name = it.data?.getStringExtra("name").toString().trim()
                if (name.isNotEmpty()) {
                    binding.textUser.text = "Hello ${name}!"
                    old_name = name
                } else {
                    name = old_name
                    Toast.makeText(applicationContext, "Update failed, name must not be empty.", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(applicationContext, "Update failed, you must press the Ok button.", Toast.LENGTH_LONG).show()
            }
        }
    }
}