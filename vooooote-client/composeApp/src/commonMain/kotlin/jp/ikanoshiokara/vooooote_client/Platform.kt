package jp.ikanoshiokara.vooooote_client

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform