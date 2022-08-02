package online.bingzi.cloud.horse.listener

import online.bingzi.cloud.horse.api.event.PlayerDownHorseEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.info

object PlayerDownHorseListener {
    @SubscribeEvent
    fun onEvent(event: PlayerDownHorseEvent){
        val player = event.player
        val horse = event.horse
        info("[下马] 玩家 ${player.name} 已经从 ${horse.name}-${horse.uniqueId} 下来")
    }
}