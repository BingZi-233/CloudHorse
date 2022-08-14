package online.bingzi.cloud.horse.command

import online.bingzi.cloud.horse.util.ModelCache
import online.bingzi.cloud.horse.util.sendLangText
import online.bingzi.cloud.horse.util.updateOwnerData
import org.bukkit.entity.Player
import taboolib.common.platform.command.subCommand

class CommandApply {
    companion object {
        val execute = subCommand {
            dynamic {
                suggestion<Player> { _, _ ->
                    ModelCache
                }
                execute<Player> { sender, _, argument ->
                    sender.updateOwnerData(argument)
                    sender.sendLangText("HorseAddModelSuccess")
                }
            }
        }
    }
}