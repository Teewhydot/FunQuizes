import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sirteefyapps.funquizes.features.quiz.presentation.screens.ConfigureQuizScreen
import com.sirteefyapps.funquizes.features.quiz.presentation.screens.QuizScreen


@Preview(showBackground = true)
@Composable
fun FunQuizAppNavigation(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = AppScreens.CONFIGURE_QUIZ_SCREEN.name
    ) {
        composable(AppScreens.CONFIGURE_QUIZ_SCREEN.name) {
            ConfigureQuizScreen(
                navController = navController,
                funQuizViewModel = null
                )
        }
    composable(route =AppScreens.QUIZ_SCREEN.name) {
        QuizScreen(navController = navController)
        }
    }
}
