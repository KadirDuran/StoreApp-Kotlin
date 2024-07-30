package com.example.storeapp

import java.io.Serializable
import java.nio.DoubleBuffer

open class Product
    (val id : Int, val title: String, val price:Double, val description:String,
    val category:String, val imgUrl:String,val rate:Double,val count:Int) : Serializable
{

}
