package com.beknur.tanda.feature.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import tandakmp.composeapp.generated.resources.Res
import tandakmp.composeapp.generated.resources.add_light
import tandakmp.composeapp.generated.resources.s4


@Composable
fun AuthContent(component: AuthComponent) {
		AuthScreen(
			onLoginClick = component::onLoginClick
		)
}




@Composable
fun AuthScreen(
	onLoginClick: (String, String) -> Unit,
	modifier: Modifier = Modifier
) {
	var emailOrPhone by remember { mutableStateOf("") }
	var password by remember { mutableStateOf("") }

	Column(
		modifier = modifier
			.background(Color.White)
			.fillMaxSize()
			.padding(16.dp),
		verticalArrangement = Arrangement.Center
	) {
		Text(
			text = "Авторизация",
			style = MaterialTheme.typography.headlineMedium,
			modifier = Modifier.padding(bottom = 24.dp)
		)

		OutlinedTextField(
			value = emailOrPhone,
			onValueChange = { emailOrPhone = it },
			label = { Text("Email или номер телефона") },
			modifier = Modifier.fillMaxWidth(),
			singleLine = true
		)

		Spacer(modifier = Modifier.height(16.dp))

		OutlinedTextField(
			value = password,
			onValueChange = { password = it },
			label = { Text("Пароль") },
			modifier = Modifier.fillMaxWidth(),
			singleLine = true,
			visualTransformation = PasswordVisualTransformation()
		)

		Spacer(modifier = Modifier.height(24.dp))

		Button(
			onClick = { onLoginClick(emailOrPhone, password) },
			modifier = Modifier.fillMaxWidth()
		) {
			Text("Войти")
		}
	}
}


@OptIn(ExperimentalResourceApi::class)
@Preview
@Composable
fun AuthContentPreview() {
	AuthScreen(
		onLoginClick = { _, _ -> }
	)
}

