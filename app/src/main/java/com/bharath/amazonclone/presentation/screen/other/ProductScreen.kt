package com.bharath.amazonclone.presentation.screen.other

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bharath.amazonclone.R
import com.bharath.amazonclone.data.entities.Products
import com.bharath.amazonclone.ui.theme.background
import com.bharath.amazonclone.ui.theme.backgroundWhite
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@Immutable
private class ImmutableProduct(val products: Products)


@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProductScreen(listVm: ProductViewModel = hiltViewModel()) {


    val product = listVm.specificProduct.collectAsState().value
    val bgcolor = if (isSystemInDarkTheme()) background else backgroundWhite
    val textClr = if (isSystemInDarkTheme()) backgroundWhite else background
    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState()
    Surface(color = bgcolor) {


        LazyColumn() {

            items(1) {


                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    Column(modifier = Modifier.fillParentMaxHeight(0.5f)) {


                        HorizontalPager(
                            count = product.images.size,
                            state = pagerState,

                            ) { page ->

                            PagerImages(url = product.images[pagerState.currentPage])

                        }
                        HorizontalPagerIndicator(
                            pagerState = pagerState,
                            indicatorShape = RectangleShape,
                            modifier = Modifier

                                .align(CenterHorizontally),
                            activeColor = MaterialTheme.colorScheme.primary,
                            spacing = 10.dp,


                            )
                    }


                    Column(modifier = Modifier.fillParentMaxHeight(0.5f)) {

                        Text(
                            text = product.name,
                            maxLines = 3,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.SemiBold
                        )


                        Text(
                            text = buildAnnotatedString {
                                withStyle(SpanStyle(fontSize = 16.sp, fontWeight = FontWeight.Light )) {
                                    append("price : ")
                                }
                                append(product.price)

                            },
                            color = textClr,
                            fontSize = 25.sp,
                            fontWeight = FontWeight.Bold
                        )

                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun PagerImages(url: String) {
    val imageurl = remember {
        mutableStateOf(url)
    }
    GlideImage(model = imageurl.value, contentDescription = "", modifier = Modifier.fillMaxSize()) {
        it.load(url)
            .useAnimationPool(true)
            .placeholder(R.drawable.baseline_image_24)
            .error(R.drawable.baseline_image_24)
    }
}