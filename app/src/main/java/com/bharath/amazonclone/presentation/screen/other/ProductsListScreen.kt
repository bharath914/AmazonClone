package com.bharath.amazonclone.presentation.screen.other

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bharath.amazonclone.data.entities.Products
import com.bharath.amazonclone.presentation.navigation.NavConsts
import com.bharath.amazonclone.presentation.navigation.NavConstsProducts
import com.bharath.amazonclone.ui.theme.background
import com.bharath.amazonclone.ui.theme.backgroundWhite
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@Composable
fun ProductsListScreen(
    navHostController: NavHostController,
    listVm: ListVm = hiltViewModel(),
) {

    val bgcolor =  backgroundWhite
    val items = listVm.specificList.collectAsState()
    Surface(color = bgcolor ) {


        LazyColumn(content = {
            val mod = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .padding(15.dp)
            items(items.value){


                ProductItem(product = it, modifier = mod ){
                    listVm.onclick(it)

                    navHostController.navigate(NavConstsProducts.productShowCaseScreen)
                }
            }
        })

    }
}
@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun ProductItem(product: Products,modifier: Modifier,onclick:()->Unit){
    Row(modifier =modifier.clickable {
        onclick()

    } ) {
        GlideImage(model = product.images.first(),
            contentDescription ="" ,
            modifier= Modifier.size(100.dp)){
            it.load(product.images.first())

        }
        Spacer(modifier = Modifier.width(20.dp))
        Column() {


            Text(
                text = product.name,
                maxLines = 3,
                style = MaterialTheme.typography.labelSmall,

            )
            Text(
                text = product.price,
                style = MaterialTheme.typography.labelLarge,

            )
        }
    }
}