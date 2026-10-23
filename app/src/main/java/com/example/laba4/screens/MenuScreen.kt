package com.example.laba4.screens

import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun MenuScreen(onNavigate: (String) -> Unit, onExit: () -> Unit) {
    val context = LocalContext.current
    val actions = listOf(
        "Старт / Пользователи" to { onNavigate("users") },
        "Профиль" to { onNavigate("profile") },
        "Настройки" to { onNavigate("settings") },
        "Примеры Compose" to { onNavigate("examples") },
        "Сообщить о проблеме" to {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "Сообщение о проблеме")
                putExtra(Intent.EXTRA_TEXT, "Описание проблемы: ")
            }
            context.startActivity(Intent.createChooser(intent, "Сообщить о проблеме"))
        },
        "Выход" to onExit
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Меню приложения", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        Text("Листайте кнопки влево и вправо", style = MaterialTheme.typography.bodySmall)
        Spacer(Modifier.height(8.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(actions, key = { it.first }) { (label, action) ->
                Button(
                    onClick = action,
                    modifier = Modifier.fillParentMaxWidth(0.75f)
                ) {
                    Text(label)
                }
            }
        }
    }
}
