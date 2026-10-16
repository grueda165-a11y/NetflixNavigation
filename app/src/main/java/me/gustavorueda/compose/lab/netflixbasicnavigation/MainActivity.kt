package me.gustavorueda.compose.lab.netflixbasicnavigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import me.gustavorueda.compose.lab.netflixbasicnavigation.ui.screen.HomeScreen
import me.gustavorueda.compose.lab.netflixbasicnavigation.ui.screen.LoginScreen
import me.gustavorueda.compose.lab.netflixbasicnavigation.ui.screen.RegisterScreen
import me.gustavorueda.compose.lab.netflixbasicnavigation.ui.screen.RegisterStep2Screen
import me.gustavorueda.compose.lab.netflixbasicnavigation.ui.screen.RegisterStep3Screen
import me.gustavorueda.compose.lab.netflixbasicnavigation.ui.theme.NetflixBasicNavigationTheme

object Routes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val REGISTER_STEP2 = "register_step2"
    const val REGISTER_STEP3 = "register_step3"
    const val HOME = "home"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NetflixBasicNavigationTheme {
                NetflixNavigation()
            }
        }
    }
}

@Composable
fun NetflixNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onSignInClick = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                onSignUpClick = { navController.navigate(Routes.REGISTER) }
            )
        }
        composable(Routes.REGISTER) {
            RegisterScreen(
                onBackClick = { navController.popBackStack() },
                onContinueClick = { navController.navigate(Routes.REGISTER_STEP2) }
            )
        }
        composable(Routes.REGISTER_STEP2) {
            RegisterStep2Screen(
                onBackClick = { navController.popBackStack() },
                onContinueClick = { navController.navigate(Routes.REGISTER_STEP3) }
            )
        }
        composable(Routes.REGISTER_STEP3) {
            RegisterStep3Screen(
                onBackClick = { navController.popBackStack() },
                onChangePlan = { navController.popBackStack() },
                onFinishClick = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                }
            )
        }
        composable(Routes.HOME) {
            HomeScreen()
        }
    }
}