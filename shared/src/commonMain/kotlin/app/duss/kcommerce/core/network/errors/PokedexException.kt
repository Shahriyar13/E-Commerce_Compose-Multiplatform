package app.duss.kcommerce.core.network.errors

enum class KCommerceError {
    ServiceUnavailable,
    ClientError,
    ServerError,
    UnknownError
}

class KCommerceException(error: KCommerceError): Exception(
    "Something goes wrong: $error"
)