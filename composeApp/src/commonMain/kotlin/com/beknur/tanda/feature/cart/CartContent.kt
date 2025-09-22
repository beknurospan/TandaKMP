package com.beknur.tanda.feature.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.beknur.tanda.feature.home.HomeComponent


@Composable
fun CartContent(component: CartComponent) {
	Box(modifier = Modifier.fillMaxSize().background(Color.Yellow))
}
