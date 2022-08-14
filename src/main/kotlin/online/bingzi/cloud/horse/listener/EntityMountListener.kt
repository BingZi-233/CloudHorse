package online.bingzi.cloud.horse.listener

import online.bingzi.cloud.horse.api.event.PlayerUpHorseEvent
import online.bingzi.cloud.horse.util.ConfigUtil
import online.bingzi.cloud.horse.util.sendLangText
import org.bukkit.entity.AbstractHorse
import org.bukkit.entity.Player
import org.spigotmc.event.entity.EntityMountEvent
import taboolib.common.platform.event.SubscribeEvent

/**
 * Horse owner find listener
 * 阻止非马的主人使用该马匹
 *
 * @constructor Create empty Horse owner find listener
 */
object EntityMountListener {
    @SubscribeEvent
    fun onEvent(event: EntityMountEvent) {
        val horse = event.mount
        val player = event.entity
        if (horse is AbstractHorse && player is Player) {
            horse.owner?.let {
                if (it.uniqueId == player.uniqueId || player.isOp || ConfigUtil.conf.getBoolean("Horse.Owner")) {
                    PlayerUpHorseEvent(player, horse).call()
                } else {
                    player.sendLangText("NonHorseOwner")
                    event.isCancelled = true
                }
            }?:player.sendLangText("HorseOwnerIsNull")
        }
    }
}