package online.bingzi.cloud.horse.api.event

import org.bukkit.entity.AbstractHorse
import org.bukkit.entity.Player
import taboolib.platform.type.BukkitProxyEvent

class PlayerUpHorseEvent(val player: Player, val horse: AbstractHorse, val tame: Boolean = false) : BukkitProxyEvent()