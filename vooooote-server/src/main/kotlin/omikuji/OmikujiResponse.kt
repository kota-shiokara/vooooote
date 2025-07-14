package jp.ikanoshiokara.omikuji

import kotlinx.serialization.Serializable

@Serializable
data class SomeOmikujiResponse(
    val totalCount: Int,
    val someOmikuji: List<OmikujiResponse>
) {
    @Serializable
    data class OmikujiResponse(
        val id: Int,
        val rank: String,
        val rankNum: Int,
        val description: String
    ) {
        companion object {
            val SAMPLE: OmikujiResponse = OmikujiResponse(
                id = 1,
                rankNum = 1,
                rank = "大吉",
                description = "今日も1日ご安全に！"
            )
        }
    }

    companion object {
        val SAMPLE: SomeOmikujiResponse = SomeOmikujiResponse(
            totalCount = 1,
            someOmikuji = listOf(
                OmikujiResponse.SAMPLE
            )
        )
    }
}