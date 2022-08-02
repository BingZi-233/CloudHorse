package online.bingzi.cloud.horse.util

import taboolib.module.configuration.Config
import taboolib.module.configuration.Configuration

object ConfigUtil {
    @Config(value = "Config.yml", migrate = true, autoReload = true)
    lateinit var confConfig: Configuration
        private set
}