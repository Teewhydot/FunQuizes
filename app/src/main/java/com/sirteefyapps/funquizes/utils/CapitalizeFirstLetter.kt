package com.sirteefyapps.funquizes.utils



fun String.capitalizeFirstLetter(): String {
    return this.replaceFirstChar { it.uppercase() }
}

fun appendBooleanDescription(initialString: String): String {
   if(initialString=="boolean")
   {return "$initialString (True/False)"}
     return initialString
}
