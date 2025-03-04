
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.sirteefyapps.funquizes.data.models.QuizModel
import com.sirteefyapps.funquizes.features.quiz.presentation.screens.ConfigureQuizScreen
import com.sirteefyapps.funquizes.features.quiz.presentation.screens.QuizScreen
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf


@Composable
fun FunQuizAppNavigation(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screens.Configure
    ) {
        composable<Screens.Configure> {
            ConfigureQuizScreen(
                navController = navController,
            )
        }



         composable<Screens.Quiz>(
        typeMap = mapOf(typeOf<QuizModel>() to QuizModelType)
    ) {
        val args  = it.toRoute<Screens.Quiz>()
        QuizScreen(quizModelFromConfigure = args.quizModel,
            navController = navController
        )
        }
    }
}



object Screens {
    // Define first destination that doesn't take any arguments
    @Serializable
    object Configure

    // Define second destination that takes a QuizModel parameter
    @Serializable
    data class Quiz(val quizModel: QuizModel)
}


val QuizModelType = object:NavType<QuizModel>(
    isNullableAllowed = true
){
    override fun get(bundle: Bundle, key: String): QuizModel? {
      return if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU){
            bundle.getParcelable(key, QuizModel::class.java)
        } else {
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): QuizModel {
        return Json.decodeFromString(Uri.decode(value))
    }

    override fun put(bundle: Bundle, key: String, value: QuizModel) {
        bundle.putParcelable(key, value)
    }

    override fun serializeAsValue(value: QuizModel): String {
            return Uri.encode(Json.encodeToString(value))
    }
}
