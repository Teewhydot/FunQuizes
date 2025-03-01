

enum class AppScreens {
    CONFIGURE_QUIZ_SCREEN,
    QUIZ_SCREEN;
    companion object {
        fun fromRoute(route: String?): AppScreens {
            return when (route?.substringBefore("/")) {
                CONFIGURE_QUIZ_SCREEN.name -> CONFIGURE_QUIZ_SCREEN
                QUIZ_SCREEN.name -> QUIZ_SCREEN
                else -> CONFIGURE_QUIZ_SCREEN
            }
        }
    }
}
