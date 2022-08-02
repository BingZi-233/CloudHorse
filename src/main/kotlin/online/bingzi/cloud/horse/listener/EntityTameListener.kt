package online.bingzi.cloud.horse.listener

import online.bingzi.cloud.horse.api.event.PlayerUpHorseEvent
import org.bukkit.Bukkit
import org.bukkit.entity.AbstractHorse
import org.bukkit.event.entity.EntityTameEvent
import taboolib.common.platform.event.SubscribeEvent

object EntityTameListener {
    @SubscribeEvent
    fun onEvent(event: EntityTameEvent) {
        val entity = event.entity
        val owner = event.owner
        if (entity is AbstractHorse) {
            Bukkit.getPlayer(owner.uniqueId)?.let { PlayerUpHorseEvent(it, entity, true).call() }
        }
    }
}