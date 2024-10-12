package com.example.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.navigation.ui.theme.NavigationTheme
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationTheme {
                Navigation()
            }
        }
    }
}

@Composable
fun Navigation() {
    var currentScreen by rememberSaveable { mutableStateOf("home") }

    Scaffold(
        topBar = {
            MyTopBar(
                currentScreen = currentScreen,
                onMenuItemClick = { screen ->
                    currentScreen = screen
                },
                onNavigationIconClick = {
                    currentScreen = "home"
                }
            )
        },
        content = { paddingValues ->
            when (currentScreen) {
                "home" -> HomeScreenContent(paddingValues)
                "info" -> InfoScreenContent(paddingValues)
                "settings" -> SettingsScreenContent(paddingValues)
            }
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    currentScreen: String,
    onMenuItemClick: (String) -> Unit,
    onNavigationIconClick: () -> Unit
) {
    var expanded by rememberSaveable { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                text = "My App",
                style = TextStyle(color = Color.White)
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                if (currentScreen != "home") {
                    onNavigationIconClick()
                } else {
                    // Voit lisätä toiminnon tähän, esimerkiksi avata valikon
                }
            }) {
                Icon(
                    imageVector = if (currentScreen != "home") Icons.Filled.ArrowBack else Icons.Filled.Menu,
                    contentDescription = if (currentScreen != "home") "Back Icon" else "Menu Icon",
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "More Icon",
                    tint = Color.White
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Info") },
                    onClick = {
                        onMenuItemClick("info")
                        expanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text("Settings") },
                    onClick = {
                        onMenuItemClick("settings")
                        expanded = false
                    }
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF6200EA),
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        )
    )
}

@Composable
fun HomeScreenContent(paddingValues: PaddingValues) {
    Text(
        text = "Sisältö koti-näkymälle",
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    )
}

@Composable
fun InfoScreenContent(paddingValues: PaddingValues) {
    Text(
        text = "Sisältö Info-näkymälle",
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    )
}

@Composable
fun SettingsScreenContent(paddingValues: PaddingValues) {
    Text(
        text = "Sisältö Asetukset-näkymälle",
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    )
}
