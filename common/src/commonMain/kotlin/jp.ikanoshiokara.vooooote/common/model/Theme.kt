package jp.ikanoshiokara.vooooote.common.model

data class Theme(
    val id: Int,
    val name: String,
    val proposals: List<Proposal>
)
