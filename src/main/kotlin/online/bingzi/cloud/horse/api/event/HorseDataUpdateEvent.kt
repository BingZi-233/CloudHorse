package online.bingzi.cloud.horse.api.event

import online.bingzi.cloud.horse.entity.HorseData
import org.bukkit.entity.AbstractHorse
import taboolib.platform.type.BukkitProxyEvent

class HorseDataUpdateEvent(val abstractHorse: AbstractHorse, val horseData: HorseData) : BukkitProxyEvent()