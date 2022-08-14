package online.bingzi.cloud.horse.api

import online.bingzi.cloud.horse.internal.entity.OwnerData
import online.bingzi.cloud.horse.internal.util.Cache.cacheOwnerData
import org.bukkit.entity.Player

/**
 * Cloud horse API
 *
 * @constructor Create empty Cloud horse API
 */
object CloudHorseAPI {
    /**
     * Get player model
     * 获取玩家模型
     *
     * @param player 玩家
     * @return 玩家模型
     */
    fun getPlayerModel(player: Player): OwnerData? {
        return cacheOwnerData[player]
    }

    /**
     * Update player model
     * 更新玩家模型
     *
     * @param player 玩家
     * @param model 玩家模型
     * @return 是否更新成功
     */
    fun updatePlayerModel(player: Player, model: String): Boolean {
        return cacheOwnerData[player]?.let {
            it.model = model
            cacheOwnerData[player] = it
            true
        } ?: false
    }
}