package online.bingzi.cloud.horse.internal.util

import online.bingzi.cloud.horse.internal.entity.OwnerData
import online.bingzi.cloud.horse.internal.util.ConfigUtil.conf
import org.bukkit.entity.Player

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
    val cacheModel: MutableList<String> = mutableListOf()

    /**
     * Cache owner data
     * 所有者数据缓存
     */
    val cacheOwnerData: MutableMap<Player, OwnerData> = mutableMapOf()

    /**
     * Cache owner limit
     * 所有者限制缓存
     */
    private var cacheOwnerLimit: Boolean? = null

    /**
     * Get cache owner limit
     * 获取所有者限制缓存
     *
     * @return cache owner limit
     */
    fun getCacheOwnerLimit(): Boolean {
        if (cacheOwnerLimit == null) {
            cacheOwnerLimit = conf.getBoolean("CloudHorse.OwnerLimit")
        }
        return cacheOwnerLimit!!
    }
}