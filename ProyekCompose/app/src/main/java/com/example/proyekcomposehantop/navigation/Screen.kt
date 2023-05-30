package com.example.proyekcomposehantop.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about")
    object PersonDetail : Screen("home/{id}") {
        fun createRoute(id: Long) = "home/$id"
    }
}