package online.bingzi.cloud.horse.listener

import online.bingzi.cloud.horse.api.event.PlayerUpHorseEvent
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.info

object PlayerUpHorseListener {
    @SubscribeEvent
    fun onEvent(event:PlayerUpHorseEvent){
        val player = event.player
        val horse = event.horse
        info("[上马]玩家 ${player.name} 已经骑上 ${horse.name}-初次驯服：${event.tame}-${horse.uniqueId}")
    }
}