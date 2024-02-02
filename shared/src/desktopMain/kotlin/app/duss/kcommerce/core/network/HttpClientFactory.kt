package app.duss.kcommerce.core.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.java.*

actual fun createPlatformHttpClient(): HttpClient {
    return HttpClient(Java)
}