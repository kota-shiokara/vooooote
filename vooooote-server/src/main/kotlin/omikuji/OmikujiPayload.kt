package jp.ikanoshiokara.omikuji

import kotlinx.serialization.Serializable

@Serializable
data class OmikujiPayload(
    val rankNum: Int,
    val description: String
) {
    companion object {
        val SAMPLE = OmikujiPayload(
            rankNum = 1,
            description = "今日も1日ご安全に！"
        )
    }
}
