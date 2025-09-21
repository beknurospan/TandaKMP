package com.beknur.tanda.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.beknur.tanda.feature.auth.AuthComponent

@Composable
fun HomeContent(component: HomeComponent) {
	Box(modifier = Modifier.fillMaxSize().background(Color.Black))
}
