package online.bingzi.cloud.horse.command

import org.bukkit.FluidCollisionMode
import org.bukkit.entity.AbstractHorse
import org.bukkit.entity.Player
import taboolib.common.platform.command.subCommand

class CommandRayTrace {
    companion object {
        val execute = subCommand {
            execute<Player> { sender, _, _ ->
                val rayTrace = sender.world.rayTrace(sender.eyeLocation, sender.eyeLocation.toVector(), 15.0, FluidCollisionMode.NEVER, true, 0.1) { t -> t is AbstractHorse }
                rayTrace?.let {
                    sender.sendMessage(sender.eyeLocation.toString())
                    sender.sendMessage(sender.eyeLocation.toVector().toString())
                    sender.sendMessage("")
                    sender.sendMessage("命中类型：Block=${it.hitBlock != null} Entity=${it.hitEntity != null}")
                    it.hitEntity?.let { entity ->
                        sender.sendMessage("输出：${entity.name}-${entity.type}-${entity.uniqueId}")
                        return@execute
                    }
                    it.hitBlock?.let { block ->
                        sender.sendMessage("输出：${block.type}-${block.location}")
                        return@execute
                    }
                } ?: sender.sendMessage("射线碰撞结果NULL，无法进行后续处理。")
            }
        }
    }
}