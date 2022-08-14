package online.bingzi.cloud.horse.internal.listener

import ltd.icecold.orangeengine.api.OrangeEngineAPI
import online.bingzi.cloud.horse.api.event.HorseApplyModelEvent
import online.bingzi.cloud.horse.api.type.HorseApplyModelType
import online.bingzi.cloud.horse.internal.util.Cache
import org.bukkit.entity.AbstractHorse
import org.bukkit.entity.Player
import org.spigotmc.event.entity.EntityMountEvent
import taboolib.common.platform.event.SubscribeEvent

/**
 * Entity mount listener
 * 实体骑乘另外一个实体的监听器
 *
 * @constructor Create empty Entity mount listener
 */
object EntityMountListener {
    @SubscribeEvent
    fun onEntityMount(event: EntityMountEvent) {
        val entity = event.entity
        val mount = event.mount
        if (entity is Player && mount is AbstractHorse) {
            Cache.cacheOwnerData[entity]?.let {
                OrangeEngineAPI.getModelManager().addNewModelEntity(mount.uniqueId, it.model)
                HorseApplyModelEvent(entity,mount,it.model,HorseApplyModelType.UP).call()
            }
        }
    }
}