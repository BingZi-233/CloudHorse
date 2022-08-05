package online.bingzi.cloud.horse.util

import online.bingzi.cloud.horse.database.DatabaseUserIndex
import online.bingzi.cloud.horse.entity.HorseData
import online.bingzi.cloud.horse.entity.OwnerData
import org.bukkit.entity.AbstractHorse
import org.bukkit.entity.Player
import taboolib.module.database.ActionDelete
import taboolib.module.database.ActionSelect
import taboolib.module.database.ActionUpdate

fun ActionSelect.extracted(ownerData: OwnerData) {
    when (DatabaseUserIndex.INSTANCE) {
        DatabaseUserIndex.NAME -> where { "name" eq ownerData.name }
        DatabaseUserIndex.UUID -> where { "uuid" eq ownerData.uuid }
    }
}

fun ActionSelect.extracted(player: Player) {
    when (DatabaseUserIndex.INSTANCE) {
        DatabaseUserIndex.NAME -> where { "name" eq player.name }
        DatabaseUserIndex.UUID -> where { "uuid" eq player.uniqueId.toString() }
    }
}

fun ActionSelect.extracted(horseData: HorseData) {
    when (DatabaseUserIndex.INSTANCE) {
        DatabaseUserIndex.NAME -> where { "name" eq horseData.name }
        DatabaseUserIndex.UUID -> where { "uuid" eq horseData.uuid }
    }
}

fun ActionSelect.extracted(abstractHorse: AbstractHorse) {
    when (DatabaseUserIndex.INSTANCE) {
        DatabaseUserIndex.NAME -> where { "name" eq abstractHorse.name }
        DatabaseUserIndex.UUID -> where { "uuid" eq abstractHorse.uniqueId.toString() }
    }
}

fun ActionUpdate.extracted(ownerData: OwnerData) {
    when (DatabaseUserIndex.INSTANCE) {
        DatabaseUserIndex.NAME -> where { "name" eq ownerData.name }
        DatabaseUserIndex.UUID -> where { "uuid" eq ownerData.uuid }
    }
}

fun ActionUpdate.extracted(player: Player) {
    when (DatabaseUserIndex.INSTANCE) {
        DatabaseUserIndex.NAME -> where { "name" eq player.name }
        DatabaseUserIndex.UUID -> where { "uuid" eq player.uniqueId.toString() }
    }
}

fun ActionUpdate.extracted(horseData: HorseData) {
    when (DatabaseUserIndex.INSTANCE) {
        DatabaseUserIndex.NAME -> where { "name" eq horseData.name }
        DatabaseUserIndex.UUID -> where { "uuid" eq horseData.uuid }
    }
}

fun ActionUpdate.extracted(abstractHorse: AbstractHorse) {
    when (DatabaseUserIndex.INSTANCE) {
        DatabaseUserIndex.NAME -> where { "name" eq abstractHorse.name }
        DatabaseUserIndex.UUID -> where { "uuid" eq abstractHorse.uniqueId.toString() }
    }
}

fun ActionDelete.extracted(ownerData: OwnerData) {
    when (DatabaseUserIndex.INSTANCE) {
        DatabaseUserIndex.NAME -> where { "name" eq ownerData.name }
        DatabaseUserIndex.UUID -> where { "uuid" eq ownerData.uuid }
    }
}

fun ActionDelete.extracted(player: Player) {
    when (DatabaseUserIndex.INSTANCE) {
        DatabaseUserIndex.NAME -> where { "name" eq player.name }
        DatabaseUserIndex.UUID -> where { "uuid" eq player.uniqueId.toString() }
    }
}

fun ActionDelete.extracted(horseData: HorseData) {
    when (DatabaseUserIndex.INSTANCE) {
        DatabaseUserIndex.NAME -> where { "name" eq horseData.name }
        DatabaseUserIndex.UUID -> where { "uuid" eq horseData.uuid }
    }
}

fun ActionDelete.extracted(abstractHorse: AbstractHorse) {
    when (DatabaseUserIndex.INSTANCE) {
        DatabaseUserIndex.NAME -> where { "name" eq abstractHorse.name }
        DatabaseUserIndex.UUID -> where { "uuid" eq abstractHorse.uniqueId.toString() }
    }
}