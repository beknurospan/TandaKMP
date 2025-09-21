package com.beknur.tanda.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import com.arkivanov.decompose.extensions.compose.stack.Children
import com.beknur.tanda.feature.auth.AuthContent
import com.beknur.tanda.feature.home.HomeContent
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun RootContent(component: RootComponent) {
	Children(
		stack = component.stack
	) {
		when (val instance = it.instance) {
			is RootComponent.Child.Auth -> {
				AuthContent(component = instance.component)
			}

			is RootComponent.Child.Home -> {
				HomeContent(component = instance.component)
			}
		}
	}

}




