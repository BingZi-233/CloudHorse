package online.bingzi.cloud.horse.internal.command

import online.bingzi.cloud.horse.internal.util.Cache.cacheOwnerData
import online.bingzi.cloud.horse.internal.util.sendLangText
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.common.platform.command.subCommand

class CommandReset {
    companion object {
        val execute = subCommand {
            dynamic {
                suggestion<CommandSender> { _, _ ->
                    Bukkit.getOnlinePlayers().map { it.name }
                }
                execute<CommandSender> { sender, _, argument ->
                    Bukkit.getPlayer(argument)?.let { player ->
                        cacheOwnerData[player]?.let {
                            it.model = "null"
                            cacheOwnerData[player] = it
                            sender.sendLangText("CommandResetSuccessToPlayer", player.name)
                        } ?: sender.sendLangText("CacheNotFoundToPlayer", player.name)
                    }
                }
            }
            execute<Player> { sender, _, _ ->
                cacheOwnerData[sender]?.let {
                    it.model = "null"
                    cacheOwnerData[sender] = it
                    sender.sendLangText("CommandResetSuccess")
                } ?: sender.sendLangText("CacheNotFound")
            }
        }
    }
}