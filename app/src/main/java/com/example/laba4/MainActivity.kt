package com.example.laba4

import android.os.Bundle
import android.widget.Toast
import android.graphics.Color as AndroidColor

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme

import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

import com.example.laba4.screens.*
import com.example.laba4.examples.*

// Главная Activity приложения
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = AndroidColor.TRANSPARENT
        window.navigationBarColor = AndroidColor.TRANSPARENT

        setContent {

            // Состояние темы приложения
            var darkTheme by rememberSaveable {
                mutableStateOf(false)
            }

            // Выбор цветовой схемы
            val colorScheme = if (darkTheme) {
                darkColorScheme()
            } else {
                lightColorScheme()
            }

            val systemBars = remember {
                WindowInsetsControllerCompat(window, window.decorView)
            }
            SideEffect {
                systemBars.isAppearanceLightStatusBars = !darkTheme
                systemBars.isAppearanceLightNavigationBars = !darkTheme
            }

            MaterialTheme(
                colorScheme = colorScheme
            ) {

                // Фон приложения зависит от выбранной темы
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .safeDrawingPadding()
                    ) {
                        AppNavigation(
                            darkTheme = darkTheme,

                            onThemeChange = { enabled ->
                                darkTheme = enabled
                            },

                            onExit = {
                                finish()
                            }
                        )
                    }
                }
            }
        }
    }
}

// Управление навигацией между экранами
@Composable
fun AppNavigation(
    darkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    onExit: () -> Unit
) {

    val context = LocalContext.current

    // Текущий экран
    var currentScreen by rememberSaveable {
        mutableStateOf("login")
    }

    // Определение предыдущего экрана
    fun previousScreen(screen: String): String {
        return when (screen) {
            "registration" -> "login"
            "menu" -> "login"

            "users",
            "profile",
            "settings",
            "examples" -> "menu"

            "text_example",
            "layout_example",
            "button_example",
            "list_example" -> "examples"

            else -> "login"
        }
    }

    // Обработка системной кнопки «Назад»
    BackHandler(
        enabled = currentScreen != "login"
    ) {

        Toast.makeText(
            context,
            "Вы вышли с окна $currentScreen",
            Toast.LENGTH_SHORT
        ).show()

        currentScreen = previousScreen(currentScreen)
    }

    // Выбор экрана приложения
    when (currentScreen) {

        "login" -> {

            LoginScreen(
                onLogin = {

                    Toast.makeText(
                        context,
                        "Вход выполнен",
                        Toast.LENGTH_SHORT
                    ).show()

                    currentScreen = "menu"
                },

                onRegister = {
                    currentScreen = "registration"
                },

                onExit = onExit
            )
        }

        "registration" -> {

            RegistrationScreen(
                onBack = {
                    currentScreen = "login"
                }
            )
        }

        "menu" -> {

            MenuScreen(
                onNavigate = { destination ->
                    currentScreen = destination
                },

                onExit = onExit
            )
        }

        "users" -> {

            UsersScreen(
                onBack = {
                    currentScreen = "menu"
                }
            )
        }

        "profile" -> {

            ProfileScreen(
                onBack = {
                    currentScreen = "menu"
                }
            )
        }

        "settings" -> {

            SettingsScreen(
                darkMode = darkTheme,

                onThemeChange = onThemeChange,

                onBack = {
                    currentScreen = "menu"
                }
            )
        }

        "examples" -> {

            ExamplesMenu(
                onNavigate = { destination ->
                    currentScreen = destination
                },

                onBack = {
                    currentScreen = "menu"
                }
            )
        }

        // Пример работы с Text и Modifier
        "text_example" -> {

            TextExample(
                onBack = {
                    currentScreen = "examples"
                }
            )
        }

        // Пример Column и Row
        "layout_example" -> {

            LayoutExample(
                onBack = {
                    currentScreen = "examples"
                }
            )
        }

        // Пример кнопки и состояния
        "button_example" -> {

            ButtonExample(
                onBack = {
                    currentScreen = "examples"
                }
            )
        }

        // Пример списка и счётчиков
        "list_example" -> {

            ListExample(
                onBack = {
                    currentScreen = "examples"
                }
            )
        }
    }
}
