package com.example.laba4.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UsersScreen(onBack: () -> Unit) {
    val users = remember { mutableStateListOf("Пользователь 1", "Пользователь 2", "Пользователь 3") }
    var nextId by rememberSaveable { mutableIntStateOf(4) }
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Список пользователей", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(12.dp))
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(users) { user ->
                Text(user, modifier = Modifier.fillMaxWidth().padding(12.dp))
                HorizontalDivider()
            }
        }
        Button(onClick = { users.add("Пользователь $nextId"); nextId++ }, modifier = Modifier.fillMaxWidth()) { Text("Добавить") }
        Button(onClick = { if (users.isNotEmpty()) users.removeAt(users.lastIndex) }, modifier = Modifier.fillMaxWidth()) { Text("Удалить последнего") }
        OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) { Text("Назад") }
    }
}
