package online.bingzi.cloud.horse.internal.database

import online.bingzi.cloud.horse.internal.entity.OwnerData
import online.bingzi.cloud.horse.internal.util.ConfigUtil
import online.bingzi.cloud.horse.internal.util.userIndex
import org.bukkit.entity.Player
import taboolib.common.platform.function.getDataFolder
import taboolib.module.database.ColumnTypeSQLite
import taboolib.module.database.Table
import taboolib.module.database.getHost
import java.io.File
import javax.sql.DataSource

/**
 * Database SQLite
 * 数据库SQLite
 *
 * @constructor Create empty Database s q lite
 */
class DatabaseSQLite : Database() {
    /**
     * Host
     */
    private val host = File(getDataFolder(), "database.db").getHost()

    /**
     * Name
     */
    private val name: String
        get() = ConfigUtil.conf.getString("database.source.SQL.table")!!

    /**
     * Data source
     */
    private val dataSource: DataSource by lazy {
        host.createDataSource()
    }

    /**
     * Table owner data
     */
    private val tableOwnerData = Table("${name}_db", host) {
        add("name") {
            type(ColumnTypeSQLite.TEXT, 64)
        }
        add("uuid") {
            type(ColumnTypeSQLite.TEXT, 64)
        }
        add("model") {
            type(ColumnTypeSQLite.TEXT, 64)
        }
    }

    init {
        tableOwnerData.workspace(dataSource) { createTable(true) }.run()
    }

    override fun insertOwnerData(ownerData: OwnerData) {
        if (tableOwnerData.find(dataSource) { userIndex(ownerData) }) {
            tableOwnerData.insert(dataSource, "name", "uuid", "model") {
                value(ownerData.name, ownerData.uuid, ownerData.model)
            }
        } else {
            updateOwnerData(ownerData)
        }
    }

    override fun selectOwnerData(player: Player): OwnerData? {
        return tableOwnerData.select(dataSource) {
            userIndex(player)
        }.firstOrNull {
            OwnerData(getString("name"), getString("uuid"), getString("model"))
        }
    }

    override fun updateOwnerData(ownerData: OwnerData) {
        if (tableOwnerData.find(dataSource) { userIndex(ownerData) }) {
            tableOwnerData.update(dataSource) {
                userIndex(ownerData)
                set("model", ownerData.model)
            }
        }else{
            insertOwnerData(ownerData)
        }
    }

    override fun deleteOwnerData(player: Player) {
        if (tableOwnerData.find(dataSource){userIndex(player)}) {
            tableOwnerData.delete(dataSource) {
                userIndex(player)
            }
        }
    }
}