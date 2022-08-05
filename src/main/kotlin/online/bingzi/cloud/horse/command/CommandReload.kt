package online.bingzi.cloud.horse.command

import ltd.icecold.orangeengine.api.OrangeEngineAPI
import online.bingzi.cloud.horse.util.ModelCache
import org.bukkit.command.CommandSender
import taboolib.common.platform.command.subCommand

class CommandReload {
    companion object {
        val execute = subCommand {
            execute<CommandSender> { sender, _, _ ->
                ModelCache.clear()
                ModelCache.addAll(OrangeEngineAPI.getModelManager().allModelData.keys)
            }
        }
    }
}