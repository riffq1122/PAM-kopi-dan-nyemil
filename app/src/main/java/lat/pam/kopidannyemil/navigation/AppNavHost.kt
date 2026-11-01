package lat.pam.kopidannyemil.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import lat.pam.kopidannyemil.data.CartState
import lat.pam.kopidannyemil.data.DrinkSize
import lat.pam.kopidannyemil.data.UserSession
import lat.pam.kopidannyemil.screens.ConfirmScreen
import lat.pam.kopidannyemil.screens.DetailScreen
import lat.pam.kopidannyemil.screens.HomeScreen
import lat.pam.kopidannyemil.screens.LoginScreen
import lat.pam.kopidannyemil.screens.OrderScreen
import lat.pam.kopidannyemil.screens.ProfileScreen
import lat.pam.kopidannyemil.screens.RegisterScreen
import lat.pam.kopidannyemil.screens.SignUpScreen
import lat.pam.kopidannyemil.screens.SplashScreen

private fun getTabOrder(route: String?): Int = when (route) {
    Routes.HOME -> 0
    Routes.ORDER -> 1
    Routes.PROFILE -> 2
    else -> -1
}

private val tabSlideSpec = spring<IntOffset>(
    dampingRatio = 0.85f,
    stiffness = Spring.StiffnessLow
)

private val tabFadeSpec = tween<Float>(durationMillis = 400)

private fun tabEnterTransition(scope: AnimatedContentTransitionScope<NavBackStackEntry>): EnterTransition {
    val initialOrder = getTabOrder(scope.initialState.destination.route)
    val targetOrder = getTabOrder(scope.targetState.destination.route)
    return if (initialOrder != -1 && targetOrder != -1) {
        val direction = if (targetOrder > initialOrder) 1 else -1
        slideInHorizontally(
            initialOffsetX = { fullWidth -> fullWidth * direction },
            animationSpec = tabSlideSpec
        ) + fadeIn(animationSpec = tabFadeSpec)
    } else {
        fadeIn(animationSpec = tabFadeSpec)
    }
}

private fun tabExitTransition(scope: AnimatedContentTransitionScope<NavBackStackEntry>): ExitTransition {
    val initialOrder = getTabOrder(scope.initialState.destination.route)
    val targetOrder = getTabOrder(scope.targetState.destination.route)
    return if (initialOrder != -1 && targetOrder != -1) {
        val direction = if (targetOrder > initialOrder) -1 else 1
        slideOutHorizontally(
            targetOffsetX = { fullWidth -> (fullWidth * direction) / 3 },
            animationSpec = tabSlideSpec
        ) + fadeOut(animationSpec = tabFadeSpec)
    } else {
        fadeOut(animationSpec = tabFadeSpec)
    }
}

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = { it / 4 },
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300))
        },
        exitTransition = {
            slideOutHorizontally(
                targetOffsetX = { -it / 4 },
                animationSpec = tween(300)
            ) + fadeOut(animationSpec = tween(300))
        },
        popEnterTransition = {
            slideInHorizontally(
                initialOffsetX = { -it / 4 },
                animationSpec = tween(300)
            ) + fadeIn(animationSpec = tween(300))
        },
        popExitTransition = {
            slideOutHorizontally(
                targetOffsetX = { it / 4 },
                animationSpec = tween(300)
            ) + fadeOut(animationSpec = tween(300))
        }
    ) {

        composable(Routes.SPLASH) {
            SplashScreen(onStartClick = { navController.navigate(Routes.SIGNUP) })
        }
        composable(Routes.SIGNUP) {
            SignUpScreen(
                onRegisterClick = { navController.navigate(Routes.REGISTER) },
                onLoginClick = { navController.navigate(Routes.LOGIN) },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginClick = { username ->
                    UserSession.name = username
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(Routes.REGISTER) {
            RegisterScreen(
                onRegisterClick = { fullName ->
                    UserSession.name = fullName
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                },
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(
            route = Routes.HOME,
            enterTransition = { tabEnterTransition(this) },
            exitTransition = { tabExitTransition(this) },
            popEnterTransition = { tabEnterTransition(this) },
            popExitTransition = { tabExitTransition(this) }
        ) {
            HomeScreen(
                onMenuClick = { selectedMenu ->
                    CartState.selectedDetailItem = selectedMenu
                    CartState.selectedSize = DrinkSize.M
                    CartState.selectedQuantity = 1
                    navController.navigate(Routes.DETAIL)
                },
                onOrderClick = { navController.navigateTab(Routes.ORDER) },
                onProfileClick = { navController.navigateTab(Routes.PROFILE) }
            )
        }
        composable(
            route = Routes.DETAIL,
            enterTransition = {
                slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(350)
                ) + fadeIn(animationSpec = tween(350))
            },
            exitTransition = {
                slideOutVertically(
                    targetOffsetY = { it },
                    animationSpec = tween(350)
                ) + fadeOut(animationSpec = tween(350))
            },
            popEnterTransition = {
                slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(350)
                ) + fadeIn(animationSpec = tween(350))
            },
            popExitTransition = {
                slideOutVertically(
                    targetOffsetY = { it },
                    animationSpec = tween(350)
                ) + fadeOut(animationSpec = tween(350))
            }
        ) {
            DetailScreen(
                onBackClick = { navController.popBackStack() },
                onBuyClick = { navController.navigate(Routes.ORDER) }
            )
        }
        composable(
            route = Routes.PROFILE,
            enterTransition = { tabEnterTransition(this) },
            exitTransition = { tabExitTransition(this) },
            popEnterTransition = { tabEnterTransition(this) },
            popExitTransition = { tabExitTransition(this) }
        ) {
            ProfileScreen(
                onSaveClick = { newName -> UserSession.name = newName },
                onLogoutClick = {
                    UserSession.name = ""
                    CartState.clear()
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                onHomeClick = { navController.navigateTab(Routes.HOME) },
                onOrderClick = { navController.navigateTab(Routes.ORDER) }
            )
        }
        composable(
            route = Routes.ORDER,
            enterTransition = { tabEnterTransition(this) },
            exitTransition = { tabExitTransition(this) },
            popEnterTransition = { tabEnterTransition(this) },
            popExitTransition = { tabExitTransition(this) }
        ) {
            OrderScreen(
                onOrderClick = { navController.navigate(Routes.CONFIRM) },
                onBackClick = { navController.popBackStack() },
                onHomeClick = { navController.navigateTab(Routes.HOME) },
                onProfileClick = { navController.navigateTab(Routes.PROFILE) }
            )
        }
        composable(Routes.CONFIRM) {
            ConfirmScreen(onOkClick = {
                CartState.clear()
                navController.navigate(Routes.HOME) {
                    popUpTo(Routes.HOME) { inclusive = true }
                }
            })
        }
    }
}

private fun NavHostController.navigateTab(route: String) {
    navigate(route) {
        popUpTo(Routes.HOME) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}
