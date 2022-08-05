package online.bingzi.cloud.horse.database

import online.bingzi.cloud.horse.entity.HorseData
import online.bingzi.cloud.horse.entity.OwnerData
import online.bingzi.cloud.horse.util.ConfigUtil
import org.bukkit.entity.AbstractHorse
import org.bukkit.entity.Player
import taboolib.module.database.ColumnOptionSQL
import taboolib.module.database.ColumnTypeSQL
import taboolib.module.database.Table
import taboolib.module.database.getHost
import javax.sql.DataSource

@Suppress("DuplicatedCode")
class DatabaseSQL : Database() {
    private val host = ConfigUtil.confConfig.getHost("database.source.SQL")

    private val name: String
        get() = ConfigUtil.confConfig.getString("database.source.SQL.table", "cloud_horse")!!

    private val dataSource: DataSource by lazy {
        host.createDataSource()
    }

    private val tableOwnerData = Table("${name}_Owner", host) {
        add { id() }
        add("name") {
            type(ColumnTypeSQL.VARCHAR, 64) {
                options(ColumnOptionSQL.UNIQUE_KEY)
            }
        }
        add("uuid") {
            type(ColumnTypeSQL.VARCHAR, 64) {
                options(ColumnOptionSQL.UNIQUE_KEY)
            }
        }
        add("model") {
            type(ColumnTypeSQL.TEXT)
        }
    }

    private val tableHorseData = Table("${name}_Horse", host) {
        add { id() }
        add("name") {
            type(ColumnTypeSQL.VARCHAR, 64) {
                options(ColumnOptionSQL.UNIQUE_KEY)
            }
        }
        add("uuid") {
            type(ColumnTypeSQL.VARCHAR, 64) {
                options(ColumnOptionSQL.UNIQUE_KEY)
            }
        }
        add("model") {
            type(ColumnTypeSQL.TEXT)
        }
        add("owner_name") {
            type(ColumnTypeSQL.VARCHAR, 64)
        }
        add("owner_uuid") {
            type(ColumnTypeSQL.VARCHAR, 64)
        }
    }

    init {
        tableHorseData.workspace(dataSource) { createTable() }.run()
        tableOwnerData.workspace(dataSource) { createTable() }.run()
    }

    private fun Player.createOwnerData() {
        tableOwnerData.insert(dataSource, "name", "uuid", "model") {
            value(name, uniqueId.toString(), "NULL")
        }
    }

    private fun AbstractHorse.createHorseData() {
        val animalTamer = owner
        if (animalTamer != null) {
            tableHorseData.insert(dataSource, "name", "uuid", "model", "owner_name", "owner_uuid") {
                value(name, uniqueId.toString(), "NULL", animalTamer.name!!, animalTamer.uniqueId.toString())
            }
        }
    }

    override fun selectPlayer(player: Player): OwnerData? {
        return tableOwnerData.workspace(dataSource) {
            select {
                rows("name", "uuid", "model", "last")
                when (DatabaseUserIndex.INSTANCE) {
                    DatabaseUserIndex.NAME -> {
                        where { "name" eq player.name }
                    }

                    DatabaseUserIndex.UUID -> {
                        where { "uuid" eq player.uniqueId.toString() }
                    }
                }
                limit(1)
            }
        }.firstOrNull {
            OwnerData(getString("name"), getString("uuid"), getString("model"))
        }
    }

    override fun updatePlayer(ownerData: OwnerData) {

    }

    override fun deletePlayer(player: Player) {
        TODO("Not yet implemented")
    }

    override fun selectHorse(abstractHorse: AbstractHorse): HorseData? {
        TODO("Not yet implemented")
    }

    override fun updateHorse(horseData: HorseData) {
        TODO("Not yet implemented")
    }

    override fun deleteHorse(abstractHorse: AbstractHorse) {
        TODO("Not yet implemented")
    }
}