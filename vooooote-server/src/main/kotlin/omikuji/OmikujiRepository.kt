package jp.ikanoshiokara.omikuji

interface OmikujiRepository {
    suspend fun create(omikuji: OmikujiPayload): SomeOmikujiResponse.OmikujiResponse

    suspend fun read(id: Int): SomeOmikujiResponse.OmikujiResponse?

    suspend fun readRandom(count: Int): SomeOmikujiResponse

    suspend fun readAll(): SomeOmikujiResponse

    suspend fun update(id: Int, omikuji: OmikujiPayload)

    suspend fun delete(id: Int)
}
