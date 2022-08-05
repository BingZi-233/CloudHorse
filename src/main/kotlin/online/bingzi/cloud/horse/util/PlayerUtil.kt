package online.bingzi.cloud.horse.util

import online.bingzi.cloud.horse.entity.OwnerData
import org.bukkit.entity.Player

/**
 * Get owner data
 * 获取玩家数据模型
 *
 * @return 玩家数据模型
 */
fun Player.getOwnerData(): OwnerData {
    return OwnerDataCache[this] ?: throw Exception("该玩家不在线或者未在缓存中，请检查玩家是否在线或者刷新缓存。")
}

/**
 * Update owner data
 * 更新玩家数据模型
 *
 * @param model 模型ID
 */
fun Player.updateOwnerData(model: String) {
    val ownerData = this.getOwnerData()
    ownerData.model = model
    OwnerDataCache[this] = ownerData
}