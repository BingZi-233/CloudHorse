package online.bingzi.cloud.horse.listener

import ltd.icecold.orangeengine.api.OrangeEngineAPI
import online.bingzi.cloud.horse.api.event.PlayerDownHorseEvent
import taboolib.common.platform.event.SubscribeEvent

object PlayerDownHorseListener {
    @SubscribeEvent
    fun onEvent(event: PlayerDownHorseEvent) {
        val player = event.player
        val horse = event.horse
        OrangeEngineAPI.getModelManager().removeModelEntity(horse.uniqueId, true)
    }
}