package com.bharath.amazonclone.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.bharath.amazonclone.presentation.screen.base.CategoriesScreen
import com.bharath.amazonclone.presentation.screen.base.Favorites
import com.bharath.amazonclone.presentation.screen.base.HomeScreen
import com.bharath.amazonclone.presentation.screen.base.ProfileScreen
import com.bharath.amazonclone.presentation.screen.other.ProductScreen
import com.bharath.amazonclone.presentation.screen.other.ProductsListScreen

@Composable
fun MyNavHostController(
    navHostController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = navHostController,

        startDestination = NavConsts.homeScreen,

        builder = {
            composable(NavConsts.homeScreen) {
                HomeScreen()
            }
            composable(NavConsts.categories) {
                CategoriesScreen(navHostController)
            }
            composable(NavConsts.favourites) {
                Favorites(navHostController)
            }
            composable(NavConsts.profile) {
                ProfileScreen(navHostController)
            }

        })

}


@Composable
fun NavHostProducts(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination =NavConstsProducts.productsListScreen, builder = {

        composable(NavConstsProducts.productsListScreen){
            ProductsListScreen(navHostController = navHostController)
        }
        composable(NavConstsProducts.productShowCaseScreen){
            ProductScreen()

        }
    } )
}