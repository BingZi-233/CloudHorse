package online.bingzi.cloud.horse.database

import online.bingzi.cloud.horse.entity.HorseData
import online.bingzi.cloud.horse.entity.OwnerData
import online.bingzi.cloud.horse.util.ConfigUtil
import online.bingzi.cloud.horse.util.extracted
import org.bukkit.entity.AbstractHorse
import org.bukkit.entity.Player
import taboolib.module.database.ColumnOptionSQL
import taboolib.module.database.ColumnTypeSQL
import taboolib.module.database.Table
import taboolib.module.database.getHost
import javax.sql.DataSource

@Suppress("DuplicatedCode")
class DatabaseSQL : Database() {
    private val host = ConfigUtil.conf.getHost("database.source.SQL")

    private val name: String
        get() = ConfigUtil.conf.getString("database.source.SQL.table", "cloud_horse")!!

    private val dataSource: DataSource by lazy {
        host.createDataSource()
    }

    private val tableOwnerData = Table("${name}_owner", host) {
        add { id() }
        add("name") {
            type(ColumnTypeSQL.VARCHAR, 64) {
                options(ColumnOptionSQL.KEY)
            }
        }
        add("uuid") {
            type(ColumnTypeSQL.VARCHAR, 64) {
                options(ColumnOptionSQL.KEY)
            }
        }
        add("model") {
            type(ColumnTypeSQL.TEXT)
        }
    }

    private val tableHorseData = Table("${name}_horse", host) {
        add { id() }
        add("name") {
            type(ColumnTypeSQL.VARCHAR, 64) {
                options(ColumnOptionSQL.KEY)
            }
        }
        add("uuid") {
            type(ColumnTypeSQL.VARCHAR, 64) {
                options(ColumnOptionSQL.KEY)
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
                rows("name", "uuid", "model")
                extracted(player)
                limit(1)
            }
        }.firstOrNull {
            OwnerData(getString("name"), getString("uuid"), getString("model"))
        }
    }

    override fun updatePlayer(ownerData: OwnerData) {
        if (tableOwnerData.find(dataSource) { extracted(ownerData) }) {
            tableOwnerData.update(dataSource) {
                extracted(ownerData)
                set("model", ownerData.model)
            }
        } else {
            tableOwnerData.insert(dataSource, "name", "uuid", "model") { value(ownerData.name, ownerData.uuid, ownerData.model) }
        }
    }


    override fun deletePlayer(player: Player) {
        if (tableOwnerData.find(dataSource) { extracted(player) }) {
            tableOwnerData.delete(dataSource) {
                extracted(player)
            }
        }
    }

    override fun selectHorse(abstractHorse: AbstractHorse): HorseData? {
        return tableHorseData.select(dataSource) {
            extracted(abstractHorse)
        }.firstOrNull {
            HorseData(getString("name"), getString("uuid"), getString("model"), getString("owner_name"), getString("owner_uuid"))
        }
    }

    override fun updateHorse(horseData: HorseData) {
        if (tableHorseData.find(dataSource) { extracted(horseData) }) {
            tableHorseData.update(dataSource) {
                extracted(horseData)
                set("model", horseData.model)
            }
        } else {
            tableHorseData.insert(dataSource, "name", "uuid", "model", "owner_name", "owner_uuid") {
                value(
                    horseData.name, horseData.uuid, horseData.model, horseData.owner_name, horseData.owner_uuid
                )
            }
        }
    }

    override fun deleteHorse(abstractHorse: AbstractHorse) {
        if (tableHorseData.find(dataSource) { extracted(abstractHorse) }) {
            tableHorseData.delete(dataSource) {
                extracted(abstractHorse)
            }
        }
    }
}