package ir.pourahmadi.weather.domain.model.news

data class TopicModel(
    val id: Int,
    val defaultChildren: Int? = null,
    val link: String? = null,
    val description: String? = null,
    val images: List<String>? = null,
    val title: String,
    val parentId: Int? = null,
    val topicTypesId: Int,
    var priority: Int = 0,
    val x: Int? = null,
    val y: Int? = null,
    val isFix: Boolean,
    val isRemovable: Boolean,
    var isSelected: Int = 0,
    var fyaxis: Int = 0,
    var fxaxis: Int = 0,
)
