package online.bingzi.cloud.horse.listener

import ltd.icecold.orangeengine.api.OrangeEngineAPI
import online.bingzi.cloud.horse.api.event.PlayerUpHorseEvent
import online.bingzi.cloud.horse.util.OwnerDataCache
import taboolib.common.platform.event.SubscribeEvent

object PlayerUpHorseListener {
    @SubscribeEvent
    fun onEvent(event: PlayerUpHorseEvent) {
        val player = event.player
        val horse = event.horse
        val ownerData = OwnerDataCache[player] ?: throw Exception("发生了内部错误！")
        OrangeEngineAPI.getModelManager().addNewModelEntity(horse.uniqueId, ownerData.model)
    }
}