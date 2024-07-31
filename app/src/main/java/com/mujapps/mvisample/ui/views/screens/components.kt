package com.mujapps.mvisample.ui.views.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.mujapps.mvisample.modal.DisplayNewsArticle
import com.mujapps.mvisample.ui.views.MainViewModel
import com.mujapps.mvisample.ui.views.NewsHeadLinesState

@Composable
fun NewsHeadingApp(mMainViewModel: MainViewModel, onRequestHeadings: () -> Unit) {
    val mState = mMainViewModel.mState.value
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally) {
        when (mState) {
            is NewsHeadLinesState.Idle -> IdleScreen(onRequestHeadings)
            is NewsHeadLinesState.Error -> {
                IdleScreen(onRequestHeadings)
                Toast.makeText(LocalContext.current, mState.error, Toast.LENGTH_SHORT).show()
            }

            is NewsHeadLinesState.Loading -> LoadingScreen()
            is NewsHeadLinesState.NewsHeadLines -> NewsHeadingList(mState.headLines)
        }
    }
}

@Composable
fun IdleScreen(onRequestHeadings: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Button(onClick = { onRequestHeadings() }) {
            Text(text = "Fetch Headlines")
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
fun NewsHeadingList(headings: List<DisplayNewsArticle>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = headings) {
            HeadLineItem(articleHeadLine = it)
            HorizontalDivider(color = Color.Gray, thickness = 1.dp, modifier = Modifier.padding(2.dp))
        }
    }
}

@Composable
fun HeadLineItem(articleHeadLine: DisplayNewsArticle) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start
        ) {
            AsyncImage(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .size(100.dp, 100.dp),
                model = articleHeadLine.mImageUrl,
                contentDescription = articleHeadLine.mTitle,
            )
            Text(text = articleHeadLine.mTitle)
        }
    }
}