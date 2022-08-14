package online.bingzi.cloud.horse

import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info

object CloudHorse : Plugin() {
    override fun onLoad() {
        info("CloudHorse is loaded")
    }

    override fun onEnable() {
        info("CloudHorse is enabled")
    }

    override fun onDisable() {
        info("CloudHorse is disabled")
    }
}