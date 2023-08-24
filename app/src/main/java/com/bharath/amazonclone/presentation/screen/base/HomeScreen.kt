package com.bharath.amazonclone.presentation.screen.base

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharath.amazonclone.R
import com.bharath.amazonclone.data.entities.Products
import com.bharath.amazonclone.presentation.viewmodel.HomeViewModel
import com.bharath.amazonclone.ui.theme.background
import com.bharath.amazonclone.ui.theme.backgroundWhite
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.load.engine.DiskCacheStrategy

@Immutable
class ImmutableLaptopList(val laptops: State<List<Products>>)

@Immutable
class ImmutableMobileList(val mobiles: State<List<Products>>)

val MaxHeight = 168f
val MinHeight = 56f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(

) {

    val scorllState = rememberScrollState()
    Scaffold(
        topBar = {
            SearchBarBox(scrollState = scorllState)

        }
    ) { padding ->
        ContentHome(paddingValues = padding, scrollState = scorllState)
    }


}

@Composable
private fun ContentHome(
    homeViewModel: HomeViewModel = hiltViewModel(),
    paddingValues: PaddingValues,
    scrollState: ScrollState,
) {

    val isLoadingLaptops = remember {
        mutableStateOf(false)
    }
    val isLoadingMobiles = remember {
        mutableStateOf(false)
    }
    isLoadingLaptops.value = homeViewModel.loadingLaptopList.collectAsState().value
    isLoadingMobiles.value = homeViewModel.loadingMobileList.collectAsState().value

    val laptopsList = ImmutableLaptopList(homeViewModel.listLaptops.collectAsState())
    val mobileList = ImmutableMobileList(homeViewModel.listMobiles.collectAsState())

    val bgcolor = if (isSystemInDarkTheme()) background else backgroundWhite
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues), color = bgcolor
    )
    {

        Column(modifier = Modifier.fillMaxSize()) {


            if (isLoadingLaptops.value && isLoadingMobiles.value) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    LinearProgressIndicator()
                }
            }


            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.verticalScroll(scrollState, true)
            ) {


                Text(
                    text = "Now Trending Laptops",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                LazyRow(content = {
                    items(laptopsList.laptops.value) {

                        ProductItem(products = it, bgcolor)
                    }
                })
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Top Tier Mobiles",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                )
                LazyRow(content = {
                    items(mobileList.mobiles.value) {
                        ProductItem(products = it, bgcolor)
                    }
                })
                LazyRow(content = {
                    items(mobileList.mobiles.value) {
                        ProductItem(products = it, bgcolor)
                    }
                })
            }

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun SearchBarBox(
    scrollState: ScrollState,
) {

    val bgcolor = if (isSystemInDarkTheme()) background else backgroundWhite
    val searchText = remember {
        mutableStateOf("")
    }
    var active by remember { mutableStateOf(false) }

    val dynamicHeight = (MaxHeight - scrollState.value).coerceIn(MinHeight, MaxHeight)

    TopAppBar(
        modifier = Modifier.heightIn(min = animateDpAsState(targetValue = dynamicHeight.dp).value)
        , backgroundColor = bgcolor
    ) {

        Column {
            Text(text = "Welcome , Bharath Prakash", color = MaterialTheme.colorScheme.onBackground)
        

        Row(horizontalArrangement = Arrangement.SpaceEvenly) {



        OutlinedTextField(value = searchText.value, onValueChange = {
            searchText.value = it
        },
            placeholder = {
                Text(text = "Search for anything here")

            },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search, contentDescription = "SearchIcon" +
                            "" +
                            ""
                )
            })
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Rounded.ShoppingCart, contentDescription = "" )
        }

        }
    }
    }


}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun ProductItem(products: Products, bgcolor: Color) {

    Card(modifier = Modifier.size(300.dp), backgroundColor = bgcolor) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GlideImage(
                model = products.images[0],
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(280.dp)
                    .background(Color.Cyan)
            ) {
                it.load(products.images.first())
                    .placeholder(R.drawable.baseline_image_24)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .centerCrop()


            }
            Text(text = products.name)
        }


    }
}