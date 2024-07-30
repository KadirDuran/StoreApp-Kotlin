package com.example.storeapp

import android.content.Intent
import android.os.Bundle
import android.provider.Telephony.Mms.Intents
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.storeapp.databinding.ActivityMainBinding
import com.example.storeapp.databinding.ActivityOneProductBinding
import java.io.Serializable
import java.util.function.LongBinaryOperator
import kotlin.time.Duration

class OneProduct : AppCompatActivity()   {
    private lateinit var binding : ActivityOneProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Binding Process Start
        binding = ActivityOneProductBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //Binding Process Finish

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val intents = intent
        val product = intents.getSerializableExtra("selectedItem") as Product
         Toast.makeText(this,"Gelen veri degeri " + product.title,Toast.LENGTH_LONG).show()
    }
}

