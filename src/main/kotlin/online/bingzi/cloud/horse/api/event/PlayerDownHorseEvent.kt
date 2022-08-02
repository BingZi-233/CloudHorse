package online.bingzi.cloud.horse.api.event

import org.bukkit.entity.AbstractHorse
import org.bukkit.entity.Player
import taboolib.platform.type.BukkitProxyEvent

class PlayerDownHorseEvent(val player: Player, val horse: AbstractHorse) : BukkitProxyEvent()