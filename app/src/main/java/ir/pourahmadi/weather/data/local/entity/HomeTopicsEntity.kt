package ir.pourahmadi.weather.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ir.pourahmadi.weather.domain.model.news.TopicModel
import ir.pourahmadi.weather.presentation.common.imagesToList


@Entity
data class HomeTopicsEntity(
    @PrimaryKey
    val id: Int,
    val defaultChildren: Int? = null,
    val link: String? = null,
    val images: String? = null,
    val description: String? = null,
    val title: String,
    val parentId: Int? = null,
    val isMovable: Boolean,
    val isRemovable: Boolean,
    val x: Int? = null,
    val y: Int? = null,
    val priority: Int,
    val topicTypesId: Int,
) {
    fun toTopicModel(): TopicModel {
        return TopicModel(
            defaultChildren = defaultChildren,
            id = id,
            link = link,
            title = title,
            images = images?.imagesToList(),
            description = description,
            parentId = parentId,
            topicTypesId = topicTypesId,
            priority = priority,
            x = x,
            y = y,
            isFix = isMovable,
            isRemovable = isRemovable
        )
    }

}