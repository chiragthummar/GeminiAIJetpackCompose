package com.md.geminiaijetpackcompose.core.util

sealed class Screen(val route : String) {
    object HomeScreen : Screen("home")
}