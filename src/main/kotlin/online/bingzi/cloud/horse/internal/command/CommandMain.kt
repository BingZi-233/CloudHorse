package online.bingzi.cloud.horse.internal.command

import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.PermissionDefault

@CommandHeader(
    name = "CloudHorse",
    aliases = ["ch"],
    permission = "CloudHorse.Command",
    permissionDefault = PermissionDefault.OP,
    description = "CloudHorse Main Command"
)
object CommandMain {
    @CommandBody(aliases = ["set"], permission = "CloudHorse.Command.Apply", permissionDefault = PermissionDefault.OP)
    val apply = CommandApply.execute

    @CommandBody(permission = "CloudHorse.Command.Reset", permissionDefault = PermissionDefault.OP)
    val reset = CommandReset.execute
}