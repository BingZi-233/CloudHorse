package online.bingzi.cloud.horse.database

import online.bingzi.cloud.horse.util.ConfigUtil

enum class DatabaseUserIndex {
    NAME, UUID;

    companion object {
        val INSTANCE: DatabaseUserIndex by lazy {
            try {
                valueOf(ConfigUtil.confConfig.getString("")!!.uppercase())
            } catch (ignored: Throwable) {
                UUID
            }
        }
    }
}