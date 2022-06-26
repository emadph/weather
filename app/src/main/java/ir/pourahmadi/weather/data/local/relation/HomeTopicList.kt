package ir.pourahmadi.weather.data.local.relation

import androidx.room.Embedded
import androidx.room.Relation
import ir.pourahmadi.weather.data.local.entity.HomeTopicsEntity
import ir.pourahmadi.weather.domain.model.topic.TopicModel
import ir.pourahmadi.weather.presentation.common.imagesToList

data class HomeTopicList(
    @Embedded val homeTopic: HomeTopicsEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "parentId"
    )
    val homeSubTopicList: List<HomeTopicsEntity>
) {
    fun toTopicModel(): TopicModel {
        return TopicModel(
            defaultChildren = homeTopic.defaultChildren,
            id = homeTopic.id,
            link = homeTopic.link,
            title = homeTopic.title,
            description = homeTopic.description,
            parentId = homeTopic.parentId,
            images = homeTopic.images?.imagesToList(),
            topicTypesId = homeTopic.topicTypesId,
            priority = homeTopic.priority,
            x = homeTopic.x,
            y = homeTopic.y,
            isFix = homeTopic.isMovable,
            isRemovable = homeTopic.isRemovable,
            topics = homeSubTopicList.map { it.toTopicModel() }
        )
    }
}
