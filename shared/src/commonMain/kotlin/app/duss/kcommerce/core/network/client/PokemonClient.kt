package app.duss.kcommerce.core.network.client

import app.duss.kcommerce.core.model.PokemonInfo
import app.duss.kcommerce.core.network.NetworkConstants
import app.duss.kcommerce.core.network.helper.handleErrors
import app.duss.kcommerce.core.network.model.PokemonResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.contentType

class PokemonClient(
    private val httpClient: HttpClient
) {

    suspend fun getPokemonList(
        page: Long,
    ): PokemonResponse {
        return handleErrors {
            httpClient.get(NetworkConstants.Pokemon.route) {
                url {
                    parameters.append("limit", PageSize.toString())
                    parameters.append("offset", (page * PageSize).toString())
                }
                contentType(ContentType.Application.Json)
            }
        }
    }

    suspend fun getPokemonByName(
        name: String,
    ): PokemonInfo {
        return handleErrors {
            httpClient.get(NetworkConstants.Pokemon.byName(name)) {
                contentType(ContentType.Application.Json)
            }
        }
    }

    companion object {
        private const val PageSize = 20
    }

}