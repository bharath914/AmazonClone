package com.bharath.amazonclone.presentation.screen.base

import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun ProfileScreen(
    navHostController: NavHostController
) {
    Surface {
        Text(text = "Profile")

    }
}
