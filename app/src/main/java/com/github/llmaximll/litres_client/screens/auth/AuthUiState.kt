package com.github.llmaximll.litres_client.screens.auth

data class AuthUiState(
    val isLoading: Boolean = false,
    val login: String = "",
    val password: String = ""
)
