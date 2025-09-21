package com.beknur.tanda.feature.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import tandakmp.composeapp.generated.resources.Res
import tandakmp.composeapp.generated.resources.add_light
import tandakmp.composeapp.generated.resources.s4


@Composable
fun AuthContent(component: AuthComponent) {
	Box(modifier = Modifier.fillMaxSize().background(Color.Cyan))
}


@OptIn(ExperimentalResourceApi::class)
@Preview
@Composable
fun AuthContentPreview() {
	Box(modifier = Modifier.fillMaxSize().background(Color.Transparent)){

		Icon(
			painter = painterResource( Res.drawable.s4),
			contentDescription = null,
			tint = Color.Black
		)
	}
}

