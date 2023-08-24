package com.bharath.amazonclone.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharath.amazonclone.data.ProductsDataBase
import com.bharath.amazonclone.data.entities.Categories
import com.bharath.amazonclone.data.entities.Products
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val products: ProductsDataBase,
) : ViewModel() {

    private val _isLoading= MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()
    private val _categories = MutableStateFlow(emptyList<Categories>())
    val categories = _categories.asStateFlow()


    init {

        viewModelScope.launch(Dispatchers.IO) {
            _categories.tryEmit(products.getAllListOfCategories())
            _isLoading.update { false }
        }
    }


}