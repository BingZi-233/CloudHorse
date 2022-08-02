package online.bingzi.cloud.horse.command

import ltd.icecold.orangeengine.api.OrangeEngineAPI
import online.bingzi.cloud.horse.util.sendLangText
import org.bukkit.entity.AbstractHorse
import org.bukkit.entity.Player
import taboolib.common.platform.command.subCommand

class CommandCancel {
    companion object {
        val execute = subCommand {
            execute<Player> { sender, _, _ ->
                val vehicle = sender.vehicle
                if (vehicle is AbstractHorse) {
                    OrangeEngineAPI.getModelManager().removeModelEntity(vehicle.uniqueId,true)
                    sender.sendLangText("HorseModelRemove")
                }
            }
        }
    }
}