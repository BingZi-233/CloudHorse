package online.bingzi.cloud.horse.entity

/**
 * Horse data
 * 马的数据
 *
 * @property name 名称
 * @property uuid UUID
 * @property model 模型
 * @property owner_name 驯服的玩家名
 * @property owner_uuid 驯服的玩家UUID
 * @constructor Create empty Horse data
 */
data class HorseData(
    val name: String,
    val uuid: String,
    var model: String = "NULL",
    val owner_name: String,
    val owner_uuid: String,
)
