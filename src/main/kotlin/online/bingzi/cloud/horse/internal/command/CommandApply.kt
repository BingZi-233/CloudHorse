package online.bingzi.cloud.horse.internal.command

import online.bingzi.cloud.horse.internal.util.Cache.cacheModel
import online.bingzi.cloud.horse.internal.util.Cache.cacheOwnerData
import online.bingzi.cloud.horse.internal.util.sendLangText
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import taboolib.common.platform.command.subCommand

/**
 * Command apple
 * 应用命令
 *
 * @constructor Create empty Command apple
 */
class CommandApply {
    companion object {
        val execute = subCommand {
            dynamic {
                suggestion<CommandSender> { _, _ ->
                    cacheModel
                }
                dynamic(optional = true) {
                    suggestion<CommandSender> { _, _ ->
                        Bukkit.getOnlinePlayers().map { it.name }
                    }
                    execute<CommandSender> { sender, context, argument ->
                        val model = context.argument(-1)
                        Bukkit.getPlayer(argument)?.let { player ->
                            cacheOwnerData[player]?.let {
                                it.model = model
                                cacheOwnerData[player] = it
                                sender.sendLangText("CommandApplySuccessToPlayer", player.name)
                            } ?: sender.sendLangText("CacheNotFoundToPlayer", player.name)
                        }
                    }
                }
                execute<Player> { sender, _, argument ->
                    cacheOwnerData[sender]?.let {
                        it.model = argument
                        cacheOwnerData[sender] = it
                        sender.sendLangText("CommandApplySuccess")
                    } ?: sender.sendLangText("CacheNotFound")
                }
            }
        }
    }
}