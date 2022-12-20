package com.example.testnewsaplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.testnewsaplication.screns.main_fragment.MainViewModel
import com.example.testnewsaplication.util.MAIN

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    private val viewModel:MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.isLoading.value
            }
        }
        setContentView(R.layout.activity_main)
        navController= Navigation.findNavController(this,R.id.nav_fragment)
        MAIN =this
    }

}