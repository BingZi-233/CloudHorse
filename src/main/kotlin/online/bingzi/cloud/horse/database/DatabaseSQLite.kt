package online.bingzi.cloud.horse.database

import online.bingzi.cloud.horse.entity.HorseData
import online.bingzi.cloud.horse.entity.OwnerData
import online.bingzi.cloud.horse.util.ConfigUtil
import org.bukkit.entity.AbstractHorse
import org.bukkit.entity.Player
import taboolib.common.platform.function.getDataFolder
import taboolib.module.database.ColumnTypeSQLite
import taboolib.module.database.Table
import taboolib.module.database.getHost
import java.io.File
import javax.sql.DataSource

@Suppress("DuplicatedCode")
class DatabaseSQLite : Database() {
    val host = File(getDataFolder(), "data.db").getHost()
    val name: String
        get() = ConfigUtil.confConfig.getString("database.source.SQL.table", "cloud_horse")!!

    private val dataSource: DataSource by lazy {
        host.createDataSource()
    }

    private val tableOwnerData = Table("${name}_Owner", host) {
        add { id() }
        add("name") {
            type(ColumnTypeSQLite.TEXT, 64)
        }
        add("uuid") {
            type(ColumnTypeSQLite.TEXT, 64)
        }
        add("model") {
            type(ColumnTypeSQLite.TEXT)
        }
        primaryKeyForLegacy += arrayOf("name", "uuid")
    }

    private val tableHorseData = Table("${name}_Horse", host) {
        add { id() }
        add("name") {
            type(ColumnTypeSQLite.TEXT, 64)
        }
        add("uuid") {
            type(ColumnTypeSQLite.TEXT, 64)
        }
        add("model") {
            type(ColumnTypeSQLite.TEXT)
        }
        add("owner_name") {
            type(ColumnTypeSQLite.TEXT, 64)
        }
        add("owner_uuid") {
            type(ColumnTypeSQLite.TEXT, 64)
        }
        primaryKeyForLegacy += arrayOf("name", "uuid")
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
        TODO("Not yet implemented")
    }

    override fun updatePlayer(ownerData: OwnerData) {
        TODO("Not yet implemented")
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