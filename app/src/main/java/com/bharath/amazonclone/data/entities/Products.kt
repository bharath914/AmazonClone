package com.bharath.amazonclone.data.entities

data class Products(
    val id: Int =1,
    val price : String ="",
    val name : String="",
    val discount : Int=0,
    val basePrice : String="",
    val details : List<String> = emptyList(),
    val images : List<String> = emptyList()
    )
