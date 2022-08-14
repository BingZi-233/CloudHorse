package online.bingzi.cloud.horse.internal.util

import online.bingzi.cloud.horse.internal.database.DatabaseUserIndex
import online.bingzi.cloud.horse.internal.entity.OwnerData
import org.bukkit.entity.Player
import taboolib.module.database.ActionDelete
import taboolib.module.database.ActionSelect
import taboolib.module.database.ActionUpdate


fun ActionSelect.userIndex(player: Player) {
    when (DatabaseUserIndex.INSTANCE) {
        DatabaseUserIndex.NAME -> where { "name" eq player.name }
        DatabaseUserIndex.UUID -> where { "uuid" eq player.uniqueId.toString() }
    }
}

fun ActionSelect.userIndex(ownerData: OwnerData) {
    when (DatabaseUserIndex.INSTANCE) {
        DatabaseUserIndex.NAME -> where { "name" eq ownerData.name }
        DatabaseUserIndex.UUID -> where { "uuid" eq ownerData.uuid }
    }
}

fun ActionUpdate.userIndex(ownerData: OwnerData) {
    when (DatabaseUserIndex.INSTANCE) {
        DatabaseUserIndex.NAME -> where { "name" eq ownerData.name }
        DatabaseUserIndex.UUID -> where { "uuid" eq ownerData.uuid }
    }
}

fun ActionDelete.userIndex(player: Player) {
    when (DatabaseUserIndex.INSTANCE) {
        DatabaseUserIndex.NAME -> where { "name" eq player.name }
        DatabaseUserIndex.UUID -> where { "uuid" eq player.uniqueId.toString() }
    }
}