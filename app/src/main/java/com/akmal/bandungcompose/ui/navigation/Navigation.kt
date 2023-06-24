package com.akmal.bandungcompose.ui.navigation

sealed class Navigation(val route: String) {
    object Home: Navigation("home")
    object Detail: Navigation("home/{detailId}") {
        fun createRoute(detailId: String) = "home/$detailId"
    }
    object About: Navigation("about_page")
}