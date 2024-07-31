package com.mujapps.mvisample.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.mujapps.mvisample.ui.theme.MviSampleTheme
import com.mujapps.mvisample.ui.views.screens.NewsHeadingApp
import com.mujapps.mvisample.utils.LoggerUtils
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        val mMainViewModel: MainViewModel = getViewModel()

        setContent {
            MviSampleTheme {
                NewsHeadingApp(mMainViewModel) {
                    lifecycleScope.launch {
                        LoggerUtils.logMessage("click Button")
                        mMainViewModel.mUserIntent.send(NewsIntent.FetchTopHeadLines)
                    }
                }
            }
        }
    }
}