package online.bingzi.cloud.horse.internal.listener

import ltd.icecold.orangeengine.api.OrangeEngineAPI
import online.bingzi.cloud.horse.api.event.HorseApplyModelEvent
import online.bingzi.cloud.horse.api.type.HorseApplyModelType
import online.bingzi.cloud.horse.internal.util.Cache
import org.bukkit.entity.AbstractHorse
import org.bukkit.entity.Player
import org.spigotmc.event.entity.EntityDismountEvent
import taboolib.common.platform.event.SubscribeEvent

object EntityDismountListener {
    @SubscribeEvent
    fun onEntityDismount(event: EntityDismountEvent) {
        val entity = event.entity
        val dismounted = event.dismounted
        if (entity is Player && dismounted is AbstractHorse) {
            OrangeEngineAPI.getModelManager().removeModelEntity(dismounted.uniqueId, true)
            HorseApplyModelEvent(entity, dismounted, Cache.cacheOwnerData[entity]?.model ?: "null", HorseApplyModelType.DOWN).call()
        }
    }
}