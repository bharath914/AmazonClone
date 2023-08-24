package com.bharath.amazonclone.presentation.screen.other

import androidx.lifecycle.ViewModel
import com.bharath.amazonclone.data.entities.Products
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val listVm: ListVm
) :ViewModel(){

    private val _specificProduct = MutableStateFlow(Products())
    val specificProduct = _specificProduct.asStateFlow()
    init {
        _specificProduct.tryEmit(
            listVm.specificProduct.value
        )

    }
}