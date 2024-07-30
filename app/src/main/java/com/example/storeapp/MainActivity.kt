package com.example.storeapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.storeapp.databinding.ActivityMainBinding
import org.json.JSONArray
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var productList : ArrayList<Product>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //Binding Process Start
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //Binding Process Finish


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        productList = ArrayList()
        getData()


    }






    fun getData()  {
        thread {
            val json = try { URL("https://fakestoreapi.com/products").readText() } catch (e: Exception) { return@thread }
            runOnUiThread {
                CreateListFormJson(json)
            }
        }
    }
    fun CreateListFormJson(data : String)
    {
        if(data!="-") {

            val jData = JSONArray(data)
            val ds  =jData.getJSONObject(0).getJSONObject("rating").get("rate");
            println(ds)
            for (i in 0..jData.length()-1) {
                productList.add(Product(
                    jData.getJSONObject(i).get("id").toString().toInt(),
                    jData.getJSONObject(i).get("title").toString(),
                    jData.getJSONObject(i).get("price").toString().toDouble(),
                    jData.getJSONObject(i).get("description").toString(),
                    jData.getJSONObject(i).get("category").toString(),
                    jData.getJSONObject(i).get("image").toString(),
                    jData.getJSONObject(i).getJSONObject("rating").get("rate").toString().toDouble(),
                    jData.getJSONObject(i).getJSONObject("rating").get("count").toString().toInt()
                ))
            }
        }
        val adapter = ProductAdapter(productList)
        binding.list.layoutManager = GridLayoutManager(this,2)
        binding.list.adapter=adapter

    }
    }