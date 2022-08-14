package online.bingzi.cloud.horse.internal.util

import org.bukkit.command.CommandSender
import taboolib.module.chat.colored
import taboolib.platform.util.asLangText

/**
 * Send lang text
 * 发送语言文本
 *
 * @param key lang key
 * @param args lang args
 */
fun CommandSender.sendLangText(key: String, vararg args: Any) {
    this.sendMessage(this.asLangText(key, *args).colored())
}

/**
 * Send lang text
 * 发送语言文本
 *
 * @param key lang key
 */
fun CommandSender.sendLangText(key: String) {
    this.sendMessage(this.asLangText(key).colored())
}