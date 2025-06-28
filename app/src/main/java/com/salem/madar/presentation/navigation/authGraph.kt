package com.salem.madar.presentation.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.salem.madar.presentation.ui.compose.screens.AddUserScreen
import com.salem.madar.presentation.ui.compose.screens.ShowUsersScreen
import kotlinx.serialization.Serializable


fun NavGraphBuilder.mainGraph(
    navController: NavHostController,
) {


    composable<AddUserScreen>(
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        },
        popExitTransition = {
            ExitTransition.None
        },
        popEnterTransition = {
            EnterTransition.None
        }
    )
    {
        AddUserScreen(navController = navController)
    }

    composable<ShowUsersScreen>(
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        },
        popExitTransition = {
            ExitTransition.None
        },
        popEnterTransition = {
            EnterTransition.None
        }
    )
    {
        ShowUsersScreen()
    }



}

@Serializable
object AddUserScreen


@Serializable
object ShowUsersScreen

