package com.example.laba4.screens

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun MenuScreen(onNavigate: (String) -> Unit, onExit: () -> Unit) {
    val context = LocalContext.current
    Column(Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("Меню приложения", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(20.dp))
        Button(onClick = { onNavigate("users") }, modifier = Modifier.fillMaxWidth()) { Text("Старт / Пользователи") }
        Button(onClick = { onNavigate("profile") }, modifier = Modifier.fillMaxWidth()) { Text("Профиль") }
        Button(onClick = { onNavigate("settings") }, modifier = Modifier.fillMaxWidth()) { Text("Настройки") }
        Button(onClick = { onNavigate("examples") }, modifier = Modifier.fillMaxWidth()) { Text("Примеры Compose") }
        Button(onClick = {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "Сообщение о проблеме")
                putExtra(Intent.EXTRA_TEXT, "Описание проблемы: ")
            }
            context.startActivity(Intent.createChooser(intent, "Сообщить о проблеме"))
        }, modifier = Modifier.fillMaxWidth()) { Text("Сообщить о проблеме") }
        OutlinedButton(onClick = onExit, modifier = Modifier.fillMaxWidth()) { Text("Выход") }
    }
}
