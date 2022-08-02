package online.bingzi.cloud.horse.command

import taboolib.common.platform.command.CommandBody
import taboolib.common.platform.command.CommandHeader
import taboolib.common.platform.command.PermissionDefault.TRUE

@CommandHeader(
    name = "CloudHorse",
    aliases = ["ch", "horse"],
    permission = "CloudHorse.use",
    permissionDefault = TRUE,
    description = "CloudHorse主命令"
)
object CommandMain {
    @CommandBody(aliases = ["gui"], permission = "CloudHorse.Command.UI", permissionDefault = TRUE)
    val ui = CommandUI.execute

    @CommandBody(permission = "CloudHorse.Command.Apple", permissionDefault = TRUE)
    val apple = CommandApply.execute

    @CommandBody(permission = "CloudHorse.Command.Cancel", permissionDefault = TRUE)
    val cancel = CommandCancel.execute

    @CommandBody(permission = "CloudHorse.Command.Admin", permissionDefault = TRUE)
    val admin = CommandAdmin.execute

    @CommandBody(permission = "CloudHorse.Command.Reload", permissionDefault = TRUE)
    val reload =  CommandReload.execute

//    @CommandBody
//    val rayTrace = CommandRayTrace.execute
}