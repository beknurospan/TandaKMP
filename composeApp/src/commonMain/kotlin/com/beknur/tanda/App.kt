package com.beknur.tanda

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import com.beknur.tanda.navigation.BottomBarItem
import com.beknur.tanda.navigation.RootComponent
import com.beknur.tanda.navigation.RootContent
import com.beknur.tanda.platform.logger
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun SausaqApp(component: RootComponent) {
	val state by component.model.collectAsState()

	logger("state ${state.isBottomBarVisible}")
	val bottomBarItems = BottomBarItem.getItems()
	Scaffold(
		bottomBar = {
			if (state.isBottomBarVisible) {
				NavigationBar {

					bottomBarItems.forEach { destination ->
						NavigationBarItem(

							selected = state.selectedTab == destination.appTab,
							onClick = { component.onClickTab(destination.appTab) },


							icon = {
								Icon(
									painter = painterResource(destination.icon), ""
								)
							},
							label = {
								Text(
									text = stringResource(destination.title),
									fontSize = 10.sp,
									maxLines = 1
								)
							},
							colors = NavigationBarItemDefaults.colors(
								indicatorColor = Color.Transparent,
								selectedIconColor = Color.Green,
								unselectedIconColor = Color.Black,
								selectedTextColor = Color.Green,
								unselectedTextColor = Color.Black
							),
						)
					}


				}
			}
		}
	) { innerPadding ->

		Box(
			modifier = Modifier
				.fillMaxSize()
				.padding(innerPadding)
		) {
			RootContent(component)
		}
	}
}

