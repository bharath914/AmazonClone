package com.bharath.amazonclone.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bharath.amazonclone.data.ProductsDataBase
import com.bharath.amazonclone.data.entities.Products


import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val products :ProductsDataBase
) : ViewModel() {


// collect the list as state to avoid null exceptions
    private val _listLaptops = MutableStateFlow(emptyList<Products>())
    val listLaptops = _listLaptops.asStateFlow()
    private val _listMobiles = MutableStateFlow(emptyList<Products>())
    val listMobiles = _listMobiles.asStateFlow()


    // to show _loading animations
    private val _loadingLaptopList = MutableStateFlow(true)
    val loadingLaptopList = _loadingLaptopList.asStateFlow()
    private val _loadingMobileList = MutableStateFlow(true)
    val loadingMobileList = _loadingMobileList.asStateFlow()








    init {

        viewModelScope.launch(Dispatchers.IO) {
            _listLaptops.tryEmit(
                products.getAllLaptops()
            )
            _loadingLaptopList.update { false }

        }
        viewModelScope.launch (Dispatchers.IO){
            _listMobiles.tryEmit(
                products.getAllMobiles()
            )
            _loadingMobileList.update { false }
        }


    }

}
