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
import com.antonioladeia.cadernetamaconica.ui.session.SessionDetailsDestination
import com.antonioladeia.cadernetamaconica.ui.session.SessionDetailsScreen
import com.antonioladeia.cadernetamaconica.ui.session.SessionEntryDestination
import com.antonioladeia.cadernetamaconica.ui.session.SessionEntryScreen

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
                navigateToSessionEntry = { navController.navigate(SessionEntryDestination.route) },
                navigateToItemUpdate = {
                    navController.navigate("${SessionDetailsDestination.route}/${it}")
                }
            )
        }
        composable(route = SessionEntryDestination.route) {
            SessionEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() }
            )
        }
        composable(
            route = SessionDetailsDestination.routeWithArgs,
            arguments = listOf(navArgument(SessionDetailsDestination.itemIdArg) {
                type = NavType.IntType
            })
        ) {
            SessionDetailsScreen(
                //navigateToEditSession = { navController.navigate("${SessionEditDestination.route}/$it") },
                navigateToEditSession = { navController.navigate("") },
                navigateBack = { navController.navigateUp() }
            )
        }
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
