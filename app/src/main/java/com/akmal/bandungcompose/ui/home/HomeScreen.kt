package com.akmal.bandungcompose.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.akmal.bandungcompose.ui.about.AboutScreen
import com.akmal.bandungcompose.ui.detail.DetailScreen
import com.akmal.bandungcompose.ui.navigation.Navigation

@Preview
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentRoute == Navigation.Home.route) {
                MyTopBarApp {
                    navController.navigate(Navigation.About.route)
                }
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Navigation.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Navigation.Home.route) {
                CardItem(navigateToDetail = { detailId ->
                    navController.navigate(Navigation.Detail.createRoute(detailId))
                }
                )
            }

            composable(Navigation.About.route) {
                AboutScreen()
            }
            composable(
                Navigation.Detail.route,
                arguments = listOf(navArgument("detailId") { type = NavType.StringType }),
            ) {
                val id = it.arguments?.getString("detailId") ?: ""
                DetailScreen(
                    idDetail = id,
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}