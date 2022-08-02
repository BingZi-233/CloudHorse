package online.bingzi.cloud.horse.command

import ltd.icecold.orangeengine.api.OrangeEngineAPI
import online.bingzi.cloud.horse.util.modelCache
import online.bingzi.cloud.horse.util.sendLangText
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import taboolib.common.platform.command.subCommand

class CommandAdmin {
    companion object {
        val execute = subCommand {
            literal("apply") {
                dynamic {
                    suggestion<CommandSender> { _, _ ->
                        Bukkit.getOnlinePlayers().map { it.name }
                    }
                    dynamic {
                        suggestion<CommandSender> { _, _ ->
                            modelCache
                        }
                        execute<CommandSender> { sender, context, argument ->
                            val playerName = context.argument(-1)
                            val modelNode = argument
                            Bukkit.getPlayer(playerName)?.let {
                                TODO("这个地方应该补充对玩家的坐骑模型更新逻辑")
                            }?:throw Exception("玩家不存在，请检查玩家是否在线！")
                        }
                    }
                }
            }
            literal("cancel") {
                dynamic {
                    suggestion<CommandSender> { _, _ ->
                        Bukkit.getOnlinePlayers().map { it.name }
                    }
                    dynamic {
                        suggestion<CommandSender> { _, _ ->
                            modelCache
                        }
                        execute<CommandSender> { sender, context, argument ->
                            val playerName = context.argument(-1)
                            val modelNode = argument
                            Bukkit.getPlayer(playerName)?.let {
                                TODO("这个地方应该补充对玩家的坐骑模型更新删除逻辑")
                            }?:throw Exception("玩家不存在，请检查玩家是否在线！")
                        }
                    }
                }
            }
            literal("add") {

            }
            literal("remove") {

            }
            literal("cache") {
                literal("reload") {
                    execute<CommandSender> { sender, context, argument ->
                        modelCache.clear()
                        modelCache.addAll(OrangeEngineAPI.getModelManager().allModelData.keys)
                        sender.sendLangText("CacheReload")
                    }
                }
            }
            literal("config") {
                literal("reload") {

                }
            }
        }
    }
}