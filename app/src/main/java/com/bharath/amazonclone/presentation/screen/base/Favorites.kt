package com.bharath.amazonclone.presentation.screen.base

import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.bharath.amazonclone.presentation.navigation.NavConsts

@Composable
fun Favorites(
    navHostController: NavHostController
) {
    Surface {
        Text(text = "Favorites")

    }
}