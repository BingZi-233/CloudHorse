package online.bingzi.cloud.horse.internal.database

import online.bingzi.cloud.horse.internal.entity.OwnerData
import online.bingzi.cloud.horse.internal.util.ConfigUtil
import online.bingzi.cloud.horse.internal.util.userIndex
import org.bukkit.entity.Player
import taboolib.module.database.ColumnOptionSQL
import taboolib.module.database.ColumnTypeSQL
import taboolib.module.database.Table
import taboolib.module.database.getHost
import javax.sql.DataSource

/**
 * Database SQL
 * 数据库SQL
 *
 * @constructor Create empty Database SQL
 */
class DatabaseSQL : Database() {
    /**
     * Host
     */
    private val host = ConfigUtil.conf.getHost("database.source.SQL")

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
        add { id() }
        add("name") {
            type(ColumnTypeSQL.VARCHAR, 64) {
                options(ColumnOptionSQL.KEY)
            }
        }
        add("uuid") {
            type(ColumnTypeSQL.VARCHAR, 64) {
                options(ColumnOptionSQL.PRIMARY_KEY)
            }
        }
        add("model") {
            type(ColumnTypeSQL.TEXT)
        }
    }

    init {
        tableOwnerData.workspace(dataSource) { createTable(true) }.run()
    }

    override fun insertOwnerData(ownerData: OwnerData) {
        if (tableOwnerData.find(dataSource) { userIndex(ownerData) }) {
            updateOwnerData(ownerData)
        } else {
            tableOwnerData.insert(dataSource, "name", "uuid", "model") {
                value(ownerData.name, ownerData.uuid, ownerData.model)
            }
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
        } else {
            insertOwnerData(ownerData)
        }
    }

    override fun deleteOwnerData(player: Player) {
        if (tableOwnerData.find(dataSource) { userIndex(player) }) {
            tableOwnerData.delete(dataSource) {
                userIndex(player)
            }
        }
    }
}