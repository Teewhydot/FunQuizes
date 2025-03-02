package com.sirteefyapps.funquizes.data.data_source

import com.sirteefyapps.funquizes.data.models.QuizModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class KtorClient {
    companion object {
        fun getClient(): HttpClient {
            return HttpClient(CIO) {
                install(ContentNegotiation) {
                    json(
                        json = Json {
                            ignoreUnknownKeys = true

                        }
                    )
                }
                install(HttpTimeout){
                    requestTimeoutMillis = 30000
                    connectTimeoutMillis = 30000
                    socketTimeoutMillis = 30000
                }
                install(DefaultRequest){
                    url {
                        host = "opentdb.com"
                        protocol = URLProtocol.HTTPS
                    }
                }
                install(Logging){
                    level = LogLevel.ALL
                    logger = Logger.ANDROID
                }
            }
        }
    }
    suspend fun getFunQuizQuestionsKtorClient(): QuizModel {
        val result =  getClient().get(
                "/api.php?amount=10&category=9&difficulty=easy&type=boolean").body<QuizModel>()
        return result
    }
}


//opentdb.com/api.php?amount=10&category=9&difficulty=easy&type=boolean
