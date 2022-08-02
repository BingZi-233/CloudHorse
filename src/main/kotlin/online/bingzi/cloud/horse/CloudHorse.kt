package online.bingzi.cloud.horse

import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info

object CloudHorse : Plugin(){
    override fun onEnable() {
        info("CloudHorse正在进行初始化操作。")
        info("鸣谢列表：")
        info("    1. 【物语云】 - 提供全程资源支持")
    }
}