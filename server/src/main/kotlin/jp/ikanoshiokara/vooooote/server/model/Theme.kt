package jp.ikanoshiokara.vooooote.server.model

import kotlinx.serialization.Serializable

@Serializable
data class Theme(
    val id: Int,
    val name: String,
    val proposals: List<Proposal>
)
