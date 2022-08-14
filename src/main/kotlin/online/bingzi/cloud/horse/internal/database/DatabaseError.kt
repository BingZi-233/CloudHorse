package online.bingzi.cloud.horse.internal.database

import online.bingzi.cloud.horse.internal.entity.OwnerData
import org.bukkit.entity.Player

/**
 * Database error
 * 数据库错误
 *
 * @constructor Create empty Database error
 */
class DatabaseError(private val cause: Throwable) : Database() {
    override fun insertOwnerData(ownerData: OwnerData) {
        throw IllegalAccessError("You can't insert data to database error. cause: ${cause.message}")
    }

    override fun selectOwnerData(player: Player): OwnerData {
        throw IllegalAccessError("You can't get data from database error. cause: ${cause.message}")
    }

    override fun updateOwnerData(ownerData: OwnerData) {
        throw IllegalAccessError("You can't update data to database error. cause: ${cause.message}")
    }

    override fun deleteOwnerData(player: Player) {
        throw IllegalAccessError("You can't delete data from database error. cause: ${cause.message}")
    }
}