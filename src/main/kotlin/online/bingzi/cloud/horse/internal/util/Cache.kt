package online.bingzi.cloud.horse.internal.util

import ltd.icecold.orangeengine.api.OrangeEngineAPI
import online.bingzi.cloud.horse.internal.entity.OwnerData
import org.bukkit.entity.Player
import taboolib.common.platform.Schedule
import taboolib.common.platform.function.warning

/**
 * Cache
 * 缓存
 *
 * @constructor Create empty Cache
 */
object Cache {
    /**
     * Cache model
     * 模型缓存
     */
    val cacheModel: MutableList<String> by lazy {
        OrangeEngineAPI.getModelManager().allModelData.keys.toMutableList()
    }

    /**
     * Cache owner data
     * 所有者数据缓存
     */
    val cacheOwnerData: MutableMap<Player, OwnerData> = mutableMapOf()

    /**
     * On load cache model
     * 更新模型缓存
     *
     */
    @Schedule(period = 100, async = true)
    fun onLoadCacheModel() {
        try {
            cacheModel.clear()
            cacheModel.addAll(OrangeEngineAPI.getModelManager().allModelData.keys)
        } catch (ignored: Throwable) {
            warning("更新模型缓存失败, 原因: ${ignored.message}")
        }
    }
}