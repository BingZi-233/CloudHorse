package online.bingzi.cloud.horse.internal.entity

/**
 * Owner data
 * 拥有者数据模型
 *
 * @property name 拥有者名称
 * @property uuid 拥有者UUID
 * @property model 拥有者模型
 * @constructor Create empty Owner data
 */
data class OwnerData(
    val name: String,
    val uuid: String,
    val model: String,
)
