package app.duss.kcommerce

import kotlinx.coroutines.CoroutineDispatcher

interface KCommerceDispatchers {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}

expect val kCommerceDispatchers: KCommerceDispatchers