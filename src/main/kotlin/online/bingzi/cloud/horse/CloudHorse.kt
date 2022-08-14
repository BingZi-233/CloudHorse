package online.bingzi.cloud.horse

import taboolib.common.platform.Plugin
import taboolib.common.platform.function.info

/**
 * Cloud horse
 * 云·马
 *
 * @constructor Create empty Cloud horse
 */
object CloudHorse : Plugin() {
    /**
     * On load
     *
     */
    override fun onLoad() {
        info("CloudHorse is loaded")
    }

    /**
     * On enable
     *
     */
    override fun onEnable() {
        info("CloudHorse is enabled")
    }

    /**
     * On disable
     *
     */
    override fun onDisable() {
        info("CloudHorse is disabled")
    }
}