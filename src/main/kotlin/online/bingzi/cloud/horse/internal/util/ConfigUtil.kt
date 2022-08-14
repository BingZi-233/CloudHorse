package online.bingzi.cloud.horse.internal.util

import taboolib.module.configuration.Config
import taboolib.module.configuration.Configuration

/**
 * Config util
 * 配置文件工具
 *
 * @constructor Create empty Config util
 */
object ConfigUtil {
    /**
     * Conf
     * 配置文件
     */
    @Config(value = "config.yml", migrate = true, autoReload = true)
    lateinit var conf: Configuration
        private set
}