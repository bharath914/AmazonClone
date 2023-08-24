package com.bharath.amazonclone.presentation.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bharath.amazonclone.ui.theme.background
import com.bharath.amazonclone.ui.theme.backgroundWhite

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyBottomNavigationBar(
    navHostController: NavHostController,
) {

    val backgroundclr = if (isSystemInDarkTheme()) background else backgroundWhite
    BottomNavigation(
        backgroundColor = backgroundclr,
        elevation = 25.dp
    ) {
        val backStackEntry by navHostController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route


        BottomNavConst.BottomNavItems.forEach {
            BottomNavigationItem(
                selected = currentRoute == it.route,
                onClick = {
                    navHostController.navigate(it.route)

                          },
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = "${it.icon}",
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                },
                label = {
                    Text(
                        text = it.label,
                        color = MaterialTheme.colorScheme.onBackground,
                        maxLines = 1,
                        modifier = Modifier.basicMarquee()
                    )
                },
                alwaysShowLabel = false
            )
        }

    }

}