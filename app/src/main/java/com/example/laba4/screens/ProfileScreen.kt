package com.example.laba4.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen(onBack: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(24.dp)) {
        Text("Профиль", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        Text("Имя: не указано")
        Text("E-mail: не указан")
        Text("Логин: не указан")
        Spacer(Modifier.height(16.dp))
        OutlinedButton(onClick = onBack) { Text("Назад") }
    }
}
