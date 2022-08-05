package online.bingzi.cloud.horse.api.event

import online.bingzi.cloud.horse.entity.OwnerData
import org.bukkit.entity.Player
import taboolib.platform.type.BukkitProxyEvent

class OwnerDataUpdateEvent(val player: Player, val ownerData: OwnerData) : BukkitProxyEvent()