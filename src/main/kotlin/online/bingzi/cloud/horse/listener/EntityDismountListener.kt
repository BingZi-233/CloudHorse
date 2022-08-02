package online.bingzi.cloud.horse.listener

import online.bingzi.cloud.horse.api.event.PlayerDownHorseEvent
import org.bukkit.entity.AbstractHorse
import org.bukkit.entity.Player
import org.spigotmc.event.entity.EntityDismountEvent
import taboolib.common.platform.event.SubscribeEvent

object EntityDismountListener {
    @SubscribeEvent
    fun onEvent(event: EntityDismountEvent){
        val horse = event.dismounted
        val player = event.entity
        if (horse is AbstractHorse && player is Player){
            PlayerDownHorseEvent(player, horse).call()
        }
    }
}