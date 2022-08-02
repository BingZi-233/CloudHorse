package online.bingzi.cloud.horse.util

import org.bukkit.command.CommandSender
import taboolib.module.chat.colored
import taboolib.platform.util.asLangText

/**
 * Send lang text
 * 发送国际化的消息
 * 简化了发送过程
 *
 * @param node 国际化节点
 */
fun CommandSender.sendLangText(node: String) {
    this.sendMessage(this.asLangText(node).colored())
}
