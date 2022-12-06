package jp.ikanoshiokara.vooooote.server.model

import kotlinx.serialization.Serializable

@Serializable
data class Proposal(
    val id: Int,
    val name: String
)
