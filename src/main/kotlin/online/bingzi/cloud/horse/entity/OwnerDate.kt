package online.bingzi.cloud.horse.entity

import online.bingzi.cloud.horse.util.modelCache
import org.bukkit.command.CommandSender
import taboolib.module.chat.colored

/**
 * Owner date
 * 马的主人
 *
 * @property username 玩家名称
 * @property uuid 玩家UUID
 * @property modelList 玩家许可模型列表
 * @constructor Create empty Owner date
 */
data class OwnerDate(
    val username: String,
    val uuid: String,
    val modelList: MutableList<String>,
) {
    /**
     * Invalid model list
     * 无效模型列表
     */
    private val invalidModelList: MutableList<String> = mutableListOf()

    fun checkModelAvailability(commandSender: CommandSender) {
        invalidModelList.clear()
        modelList.forEach {
            if (!modelCache.contains(it)) {
                invalidModelList.add(it)
            }
        }
        commandSender.sendMessage("&a&l无效模型检查完成！共计出现 ${invalidModelList.size} 个模型！".colored())
    }

    fun removeModelAvailability(commandSender: CommandSender) {
        if (modelList.removeAll(invalidModelList)) {
            commandSender.sendMessage("&a&l无效模型已清除".colored())
        } else {
            commandSender.sendMessage("&a&l暂无无效模型存在".colored())
        }
    }
}
