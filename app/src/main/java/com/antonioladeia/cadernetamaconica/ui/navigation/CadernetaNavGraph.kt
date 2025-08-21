package com.antonioladeia.cadernetamaconica.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.antonioladeia.cadernetamaconica.ui.home.HomeDestination
import com.antonioladeia.cadernetamaconica.ui.home.HomeScreen

@Composable
fun CadernetaNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable(route = HomeDestination.route) {
            HomeScreen(
//                navigateToItemEntry = { navController.navigate(SessionEntryDestination.route) },
//                navigateToItemUpdate = {
//                    navController.navigate("${SessionDetailsDestination.route}/${it}")
//                }
            )
        }
//        composable(route = SessionEntryDestination.route) {
//            ItemEntryScreen(
//                navigateBack = { navController.popBackStack() },
//                onNavigateUp = { navController.navigateUp() }
//            )
//        }
//        composable(
//            route = SessionDetailsDestination.routeWithArgs,
//            arguments = listOf(navArgument(SessionDetailsDestination.itemIdArg) {
//                type = NavType.IntType
//            })
//        ) {
//            SessionDetailsScreen(
//                navigateToEditSession = { navController.navigate("${SessionEditDestination.route}/$it") },
//                navigateBack = { navController.navigateUp() }
//            )
//        }
//        composable(
//            route = SessionEditDestination.routeWithArgs,
//            arguments = listOf(navArgument(SessionEditDestination.itemIdArg) {
//                type = NavType.IntType
//            })
//        ) {
//            SessionEditScreen(
//                navigateBack = { navController.popBackStack() },
//                onNavigateUp = { navController.navigateUp() }
//            )
//        }
    }
}
