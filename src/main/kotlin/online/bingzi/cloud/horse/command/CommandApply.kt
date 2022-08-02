package online.bingzi.cloud.horse.command

import ltd.icecold.orangeengine.api.OrangeEngineAPI
import online.bingzi.cloud.horse.util.modelCache
import online.bingzi.cloud.horse.util.sendLangText
import org.bukkit.entity.AbstractHorse
import org.bukkit.entity.Player
import taboolib.common.platform.command.subCommand

class CommandApply {
    companion object {
        val execute = subCommand {
            dynamic {
                suggestion<Player> { _, _ ->
                    modelCache
                }
                execute<Player> { sender, _, argument ->
                    val vehicle = sender.vehicle
                    if (vehicle is AbstractHorse) {
                        vehicle.owner?.let {
                            if (it.uniqueId == sender.uniqueId) {
                                OrangeEngineAPI.getModelManager().addNewModelEntity(vehicle.uniqueId, argument)
                                sender.sendLangText("HorseAddModelSuccess")
                            }
                        } ?: sender.sendLangText("HorseAddModelError")
                    } else {
                        sender.sendLangText("HorseTypeError")
                    }
                }
            }
        }
    }
}