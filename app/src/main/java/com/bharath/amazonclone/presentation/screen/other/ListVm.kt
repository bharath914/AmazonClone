package com.bharath.amazonclone.presentation.screen.other

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharath.amazonclone.data.ProductsDataBase
import com.bharath.amazonclone.data.entities.Products
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListVm @Inject constructor(
    private val productsDataBase: ProductsDataBase,
) : ViewModel() {
    private val _SpecificList = MutableStateFlow(emptyList<Products>())
    val specificList = _SpecificList.asStateFlow()

    private val _specificProduct = MutableStateFlow(Products())
    val specificProduct = _specificProduct.asStateFlow()
    init {
        viewModelScope.launch (Dispatchers.IO){
        _SpecificList.tryEmit(
            productsDataBase.getAllMobiles()
        )

        }

    }

    fun onclick(products: Products){




        _specificProduct.tryEmit( products)


    }
}