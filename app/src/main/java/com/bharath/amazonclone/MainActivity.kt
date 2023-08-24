package com.bharath.amazonclone

import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.bharath.amazonclone.presentation.navigation.MyBottomNavigationBar
import com.bharath.amazonclone.presentation.navigation.MyNavHostController
import com.bharath.amazonclone.presentation.navigation.NavConsts
import com.bharath.amazonclone.ui.theme.AmazonCloneTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AmazonCloneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {


                    val navcontroller = rememberNavController()
                    Scaffold(bottomBar = {
                        MyBottomNavigationBar(navHostController = navcontroller)
                    }, content = {
                        MyNavHostController(navHostController =navcontroller , paddingValues =it )
                    })




                }
            }
            hideSystemUi()
        }
    }
    fun hideSystemUi(){
        actionBar?.hide()
        WindowCompat.setDecorFitsSystemWindows(window,false)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R){
            window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }else{
            window.insetsController?.apply {
                hide(android.view.WindowInsets.Type.statusBars())
                systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

            }
        }
    }

}

