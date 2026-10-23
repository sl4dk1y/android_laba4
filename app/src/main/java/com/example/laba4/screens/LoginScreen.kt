package com.example.laba4.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(onLogin: () -> Unit, onRegister: () -> Unit, onExit: () -> Unit) {
    var login by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    Column(Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Text("Добро пожаловать!", fontSize = 26.sp)
        Spacer(Modifier.height(20.dp))
        Text("👤", fontSize = 64.sp)
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(login, { login = it }, label = { Text("Логин") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        Spacer(Modifier.height(10.dp))
        OutlinedTextField(password, { password = it }, label = { Text("Пароль") }, modifier = Modifier.fillMaxWidth(), singleLine = true, visualTransformation = PasswordVisualTransformation())
        Spacer(Modifier.height(18.dp))
        Button(onClick = onLogin, modifier = Modifier.fillMaxWidth()) { Text("Вход") }
        Button(onClick = onRegister, modifier = Modifier.fillMaxWidth()) { Text("Регистрация") }
        OutlinedButton(onClick = onExit, modifier = Modifier.fillMaxWidth()) { Text("Выход") }
    }
}
