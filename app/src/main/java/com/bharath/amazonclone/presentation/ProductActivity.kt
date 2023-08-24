package com.bharath.amazonclone.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.bharath.amazonclone.presentation.navigation.NavHostProducts
import com.bharath.amazonclone.ui.theme.AmazonCloneTheme
import com.bharath.amazonclone.ui.theme.backgroundWhite
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmazonCloneTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = backgroundWhite

                ) {


                    val navHostController = rememberNavController()
                    Scaffold(
                        content = {
                            it
                            NavHostProducts(navHostController = navHostController)
                        }, containerColor = backgroundWhite
                    )

                }
            }
        }
    }
}