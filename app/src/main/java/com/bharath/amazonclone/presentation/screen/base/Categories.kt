package com.bharath.amazonclone.presentation.screen.base

import android.content.Intent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bharath.amazonclone.MainActivity
import com.bharath.amazonclone.data.entities.Categories
import com.bharath.amazonclone.presentation.ProductActivity
import com.bharath.amazonclone.presentation.navigation.NavConsts
import com.bharath.amazonclone.presentation.viewmodel.CategoryViewModel
import com.bharath.amazonclone.ui.theme.background
import com.bharath.amazonclone.ui.theme.backgroundWhite
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.api.Distribution.BucketOptions.Linear

@Immutable
class CategoriesList(val list: List<Categories>)

@Composable
fun CategoriesScreen(
    navHostController: NavHostController,
    categoryViewModel: CategoryViewModel = hiltViewModel(),
) {

    val bgcolor = if (isSystemInDarkTheme()) background else backgroundWhite
    val isLoading = categoryViewModel.isLoading.collectAsState()
    val list = CategoriesList(categoryViewModel.categories.collectAsState().value)

    Surface(color = bgcolor) {
        Column(modifier = Modifier.fillMaxSize()) {

            AnimatedVisibility(visible = isLoading.value) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center, content = {
                    LinearProgressIndicator(color = MaterialTheme.colorScheme.primary)
                })
            }

            val context = LocalContext.current
            LazyVerticalGrid(columns = GridCells.Fixed(2),
                content = {
                    val mod = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, end = 15.dp)
                    items(list.list) {

                        CollectionItemLayout(it,mod){


                            val intent = Intent(context,ProductActivity::class.java)
                            context.startActivity(intent)
//                            navHostController.navigate(NavConsts.productsListScreen)
                        }
                    }
                })
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun CollectionItemLayout(categories: Categories,modifier: Modifier,onclicklistener :()->Unit) {

    Column(modifier=modifier.clickable {
        onclicklistener()
    }) {
        GlideImage(model = categories.imageUrl, contentDescription ="",
            modifier
                .height(120.dp)
                .clip(
                    RoundedCornerShape(15)
                )
                .background(Color.Cyan) ){
            it.load(categories.imageUrl)
                .centerCrop()
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = categories.name, color = MaterialTheme.colorScheme.onBackground)
    }
    
}
