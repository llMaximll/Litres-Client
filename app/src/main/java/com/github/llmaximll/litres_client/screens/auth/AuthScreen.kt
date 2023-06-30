package com.github.llmaximll.litres_client.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.llmaximll.litres_client.R.string as AppText
import com.github.llmaximll.litres_client.common.composable.BasicField
import com.github.llmaximll.litres_client.common.composable.LogoText

@Composable
fun AuthScreen(
    navigateToScreen: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        if (uiState.isLoading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LogoText(
                fontSize = 38.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                modifier = Modifier
                    .padding(horizontal = 48.dp),
                text = "Please enter your login and enter password",
                color = Color.Gray,
                style = MaterialTheme.typography.labelLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

            BasicField(
                placeholder = AppText.auth_login_hint,
                modifier = Modifier,
                value = uiState.login,
                onValueChange = { viewModel.onLoginChange(it) },
                leadingIcon = Icons.Default.AccountCircle
            )

            Spacer(modifier = Modifier.height(8.dp))

            BasicField(
                placeholder = AppText.auth_password_hint,
                modifier = Modifier,
                value = uiState.password,
                onValueChange = { viewModel.onPasswordChange(it) },
                leadingIcon = Icons.Default.Lock,
                keyboardType = KeyboardType.Password
            )

            Spacer(modifier = Modifier.height(24.dp))

            TextButton(
                onClick = {

                }
            ) {
                Text(text = stringResource(id = AppText.auth_forgot_password))
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    viewModel.onAuthButton(navigateToScreen)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 64.dp),
                contentPadding = PaddingValues(vertical = 16.dp)
            ) {
                Text(text = stringResource(id = AppText.auth_login))
            }

            Spacer(modifier = Modifier.height(24.dp))

            TextButton(
                onClick = {

                }
            ) {
                Text(
                    text = buildAnnotatedString {
                        append(stringResource(id = AppText.auth_sign_up_1))
                        append(" ")
                        withStyle(style = SpanStyle(color = Color.Blue)) {
                            append(stringResource(id = AppText.auth_sign_up_2))
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewAuthScreen() {
    AuthScreen(navigateToScreen = {  })
}

