package online.bingzi.cloud.horse.api

import online.bingzi.cloud.horse.util.OwnerDataCache
import org.bukkit.entity.Player

/**
 * Cloud horse API
 * 里面没啥东西，就是占个位置不让IDEA合并包路径
 *
 * @constructor Create empty Cloud horse a p i
 */
object CloudHorseAPI {
    fun playerApplyModel(player: Player, model: String) {
        val ownerData = OwnerDataCache[player] ?: throw Exception("该玩家不在线或者未在缓存中，请检查玩家是否在线或者刷新缓存。")
        ownerData.model = model
        OwnerDataCache[player] = ownerData
    }

    fun playerClearModel(player: Player) {
        OwnerDataCache[player] ?: throw Exception("该玩家不在线或者未在缓存中，请检查玩家是否在线或者刷新缓存。")
    }
}