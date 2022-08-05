package online.bingzi.cloud.horse.util

import ltd.icecold.orangeengine.api.OrangeEngineAPI
import online.bingzi.cloud.horse.entity.HorseData
import online.bingzi.cloud.horse.entity.OwnerData
import org.bukkit.entity.AbstractHorse
import org.bukkit.entity.Player

/**
 * Model cache
 * 模型名称缓存
 */
val ModelCache: MutableList<String> = mutableListOf<String>().apply {
    this.addAll(OrangeEngineAPI.getModelManager().allModelData.keys)
}

/**
 * Owner data cache
 * 玩家数据模型缓存
 */
val OwnerDataCache: MutableMap<Player, OwnerData> = mutableMapOf()

/**
 * Horse data cache
 * 马数据模型缓存
 */
val HorseDataCache: MutableMap<AbstractHorse, HorseData> = mutableMapOf()