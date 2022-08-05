package online.bingzi.cloud.horse.entity

/**
 * Owner date
 * 马的主人
 *
 * @property name 名称
 * @property uuid UUID
 * @property model 使用的模型
 * @constructor Create empty Owner date
 */
data class OwnerData(
    val name: String,
    val uuid: String,
    var model: String = "NULL",
)
