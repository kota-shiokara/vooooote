package jp.ikanoshiokara.omikuji

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.Random
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class OmikujiRepositoryImpl(database: Database): OmikujiRepository {
    companion object {
        val RANK_LIST = listOf<String>(
            "超大吉", "大吉", "中吉", "吉", "凶"
        )
    }

    object OmikujiObj : Table() {
        val id = integer("id").autoIncrement()
        val rank = varchar("rank", length = 16)
        val rankNum = integer("rank_num")
        val description = varchar("name", length = 100)

        override val primaryKey = PrimaryKey(id)
    }

    init {
        transaction(database) {
            SchemaUtils.create(OmikujiObj)
        }
    }

    override suspend fun create(omikuji: OmikujiPayload): SomeOmikujiResponse.OmikujiResponse = dbQuery {
        val rankWord = RANK_LIST[omikuji.rankNum]
        val id = OmikujiObj.insert {
            it[rank] = rankWord
            it[rankNum] = omikuji.rankNum
            it[description] = omikuji.description
        }[OmikujiObj.id]

        SomeOmikujiResponse.OmikujiResponse(
            id = id,
            rank = rankWord,
            rankNum = omikuji.rankNum,
            description = omikuji.description
        )
    }

    override suspend fun read(id: Int): SomeOmikujiResponse.OmikujiResponse? {
        return dbQuery {
            OmikujiObj.selectAll()
                .where { OmikujiObj.id eq id }
                .map {
                    SomeOmikujiResponse.OmikujiResponse(
                        id = it[OmikujiObj.id],
                        rank = it[OmikujiObj.rank],
                        rankNum = it[OmikujiObj.rankNum],
                        description = it[OmikujiObj.description]
                    )
                }
                .singleOrNull()
        }
    }

    override suspend fun readRandom(count: Int): SomeOmikujiResponse {
        return dbQuery {
            val someOmikuji = OmikujiObj.selectAll()
                .orderBy(Random())
                .take(count)
                .map {
                    SomeOmikujiResponse.OmikujiResponse(
                        id = it[OmikujiObj.id],
                        rank = it[OmikujiObj.rank],
                        rankNum = it[OmikujiObj.rankNum],
                        description = it[OmikujiObj.description]
                    )
                }

            SomeOmikujiResponse(
                totalCount = someOmikuji.size,
                someOmikuji = someOmikuji
            )
        }
    }

    override suspend fun readAll(): SomeOmikujiResponse {
        return dbQuery {
            val someOmikuji = OmikujiObj.selectAll()
                .map {
                    SomeOmikujiResponse.OmikujiResponse(
                        id = it[OmikujiObj.id],
                        rank = it[OmikujiObj.rank],
                        rankNum = it[OmikujiObj.rankNum],
                        description = it[OmikujiObj.description]
                    )
                }
            SomeOmikujiResponse(
                totalCount = someOmikuji.size,
                someOmikuji = someOmikuji
            )
        }
    }

    override suspend fun update(id: Int, omikuji: OmikujiPayload) {
        dbQuery {
            OmikujiObj.update({ OmikujiObj.id eq id }) {
                it[rankNum] = omikuji.rankNum
                it[rank] = RANK_LIST[omikuji.rankNum]
                it[description] = omikuji.description
            }
        }
    }

    override suspend fun delete(id: Int) {
        dbQuery {
            OmikujiObj.deleteWhere { OmikujiObj.id eq id }
        }
    }
}

suspend fun <T> dbQuery(block: suspend () -> T): T =
    newSuspendedTransaction(Dispatchers.IO) { block() }

