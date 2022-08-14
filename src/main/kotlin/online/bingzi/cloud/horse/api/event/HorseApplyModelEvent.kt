package online.bingzi.cloud.horse.api.event

import org.bukkit.entity.AbstractHorse
import org.bukkit.entity.Player
import taboolib.platform.type.BukkitProxyEvent

class HorseApplyModelEvent(val player: Player, val abstractHorse: AbstractHorse, val model: String) : BukkitProxyEvent()