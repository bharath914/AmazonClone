package com.bharath.amazonclone.data


import com.bharath.amazonclone.data.entities.Categories
import com.bharath.amazonclone.data.entities.Products
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class ProductsDataBase {

    private val firestore = FirebaseFirestore.getInstance()
    private val laptopsCollection =firestore.collection(ProductsConstants.laptops_collection_name)
    private val mobilesCollection = firestore.collection(ProductsConstants.mobiles_collection_naem)
    private val categoriesCollection = firestore.collection(ProductsConstants.categories)

    suspend fun getAllMobiles():List<Products>{
        return try {
            mobilesCollection.get().await().toObjects(Products::class.java)

        }
        catch (e:Exception){
            e.printStackTrace()
            emptyList<Products>()
        }
    }
    suspend fun getAllLaptops():List<Products>{

        return try {
            laptopsCollection.get().await().toObjects(Products::class.java)

        }catch (e:Exception){
            e.printStackTrace()
            emptyList<Products>()
        }

    }

    suspend fun getAllListOfCategories():List<Categories>{
        return try {
        categoriesCollection.get().await().toObjects(Categories::class.java)
        }catch (e:Exception){
            e.printStackTrace()
            emptyList<Categories>()
        }
    }
}

object ProductsConstants{
    const val laptops_collection_name= "laptops"
    const val mobiles_collection_naem = "mobiles"
    const val categories = "categories"
}