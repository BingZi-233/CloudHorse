package online.bingzi.cloud.horse.command

import online.bingzi.cloud.horse.util.sendLangText
import online.bingzi.cloud.horse.util.updateOwnerData
import org.bukkit.entity.Player
import taboolib.common.platform.command.subCommand

class CommandCancel {
    companion object {
        val execute = subCommand {
            execute<Player> { sender, _, _ ->
                sender.updateOwnerData("NULL")
                sender.sendLangText("HorseModelRemove")
            }
        }
    }
}