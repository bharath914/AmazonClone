package com.bharath.amazonclone.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ViewCozy
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItems(
    val label:String,
    val icon: ImageVector,
    val route:String
)
object BottomNavConst{
    val BottomNavItems = listOf(
        BottomNavItems(
            label = "Home",
            icon = Icons.Rounded.Home,
            route = NavConsts.homeScreen
        ),
        BottomNavItems(
            label = "Categories",
            icon = Icons.Rounded.ViewCozy,
            route = NavConsts.categories
        ),
        BottomNavItems(
            label = "Favorites",
            icon = Icons.Rounded.Favorite,
            route = NavConsts.favourites
        ),
        BottomNavItems(
            label = "Profile",
            icon = Icons.Rounded.Person,
            route = NavConsts.profile
        )
    )
}

