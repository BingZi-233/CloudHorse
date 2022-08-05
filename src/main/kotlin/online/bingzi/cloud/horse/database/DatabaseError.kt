package online.bingzi.cloud.horse.database

import online.bingzi.cloud.horse.entity.HorseData
import online.bingzi.cloud.horse.entity.OwnerData
import org.bukkit.entity.AbstractHorse
import org.bukkit.entity.Player

class DatabaseError(private val cause: Throwable) : Database() {
    override fun selectPlayer(player: Player): OwnerData {
        throw IllegalAccessError("Database initialization failed: ${cause.localizedMessage}")
    }

    override fun updatePlayer(ownerData: OwnerData) {
        throw IllegalAccessError("Database initialization failed: ${cause.localizedMessage}")
    }

    override fun deletePlayer(player: Player) {
        throw IllegalAccessError("Database initialization failed: ${cause.localizedMessage}")
    }

    override fun selectHorse(abstractHorse: AbstractHorse): HorseData {
        throw IllegalAccessError("Database initialization failed: ${cause.localizedMessage}")
    }

    override fun updateHorse(horseData: HorseData) {
        throw IllegalAccessError("Database initialization failed: ${cause.localizedMessage}")
    }

    override fun deleteHorse(abstractHorse: AbstractHorse) {
        throw IllegalAccessError("Database initialization failed: ${cause.localizedMessage}")
    }
}