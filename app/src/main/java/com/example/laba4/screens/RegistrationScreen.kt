package com.example.laba4.screens

import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegistrationScreen(onBack: () -> Unit) {
    val context = LocalContext.current
    var login by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var fullName by rememberSaveable { mutableStateOf("") }
    var birthDate by rememberSaveable { mutableStateOf("") }
    var gender by rememberSaveable { mutableStateOf("") }
    var avatarUri by rememberSaveable { mutableStateOf<String?>(null) }
    val picker = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        avatarUri = uri?.toString()
    }
    Column(Modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Регистрация пользователя", fontSize = 24.sp)
        Spacer(Modifier.height(18.dp))
        OutlinedTextField(login, { login = it }, label = { Text("Логин") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        OutlinedTextField(password, { password = it }, label = { Text("Пароль") }, modifier = Modifier.fillMaxWidth(), singleLine = true, visualTransformation = PasswordVisualTransformation(), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password))
        OutlinedTextField(fullName, { fullName = it }, label = { Text("ФИО") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        OutlinedTextField(birthDate, { birthDate = it }, label = { Text("Дата рождения (ДД.ММ.ГГГГ)") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        Spacer(Modifier.height(12.dp))
        Text("Пол:", modifier = Modifier.fillMaxWidth())
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = gender == "Мужской", onClick = { gender = "Мужской" })
            Text("Мужской")
            RadioButton(selected = gender == "Женский", onClick = { gender = "Женский" })
            Text("Женский")
        }
        Spacer(Modifier.height(12.dp))
        val bitmap = remember(avatarUri) {
            avatarUri?.let { path ->
                try { context.contentResolver.openInputStream(Uri.parse(path))?.use { BitmapFactory.decodeStream(it) } }
                catch (e: Exception) { Log.e("Laba4", "Ошибка аватара", e); null }
            }
        }
        if (bitmap != null) {
            Image(bitmap.asImageBitmap(), contentDescription = "Аватар", modifier = Modifier.size(120.dp).clip(CircleShape), contentScale = ContentScale.Crop)
        } else {
            Box(Modifier.size(120.dp), contentAlignment = Alignment.Center) { Text("👤", fontSize = 64.sp) }
        }
        Button(onClick = { picker.launch("image/*") }) { Text("Выбрать аватар") }
        Spacer(Modifier.height(12.dp))
        Button(onClick = {
            if (listOf(login, password, fullName, birthDate, gender).any { it.isBlank() }) {
                Toast.makeText(context, "Заполните все поля", Toast.LENGTH_SHORT).show()
            } else {
                Log.d("Laba4", "Регистрация: логин=$login, ФИО=$fullName, дата рождения=$birthDate, пол=$gender")
                Toast.makeText(context, "Данные регистрации проверены", Toast.LENGTH_SHORT).show()
            }
        }, modifier = Modifier.fillMaxWidth()) { Text("Зарегистрироваться") }
        OutlinedButton(onClick = onBack, modifier = Modifier.fillMaxWidth()) { Text("Назад") }
    }
}
