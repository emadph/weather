package ir.pourahmadi.weather.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import ir.pourahmadi.weather.data.local.entity.TopicEntity
import ir.pourahmadi.weather.domain.model.topic.TopicModel
import ir.pourahmadi.weather.presentation.common.imagesToList

data class TopicList(
    @Embedded val topic: TopicEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "parentId"
    )
    val subTopicList: List<TopicEntity>
) {
    fun toTopicModel(): TopicModel {
        return TopicModel(
            defaultChildren = topic.defaultChildren,
            id = topic.id,
            link = topic.link,
            title = topic.title,
            description = topic.description,
            parentId = topic.parentId,
            images = topic.images?.imagesToList(),
            topicTypesId = topic.topicTypesId,
            priority = topic.priority,
            x = topic.x,
            y = topic.y,
            isFix = topic.isMovable,
            isRemovable = topic.isRemovable,
            topics = subTopicList.map { it.toTopicModel() }
        )
    }
}
