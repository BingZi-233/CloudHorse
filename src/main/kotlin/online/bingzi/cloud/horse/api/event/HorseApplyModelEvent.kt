package online.bingzi.cloud.horse.api.event

import online.bingzi.cloud.horse.api.type.HorseApplyModelType
import org.bukkit.entity.AbstractHorse
import org.bukkit.entity.Player
import taboolib.platform.type.BukkitProxyEvent

/**
 * Horse apply model event
 * 马应用模型事件
 *
 * @property player 玩家
 * @property abstractHorse 马
 * @property model 模型
 * @property type 模型类型
 * @constructor Create empty Horse apply model event
 */
class HorseApplyModelEvent(val player: Player, val abstractHorse: AbstractHorse, val model: String, val type: HorseApplyModelType) : BukkitProxyEvent()