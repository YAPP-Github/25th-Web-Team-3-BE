package com.coffee.api.cafe.infrastructure

import com.coffee.api.cafe.domain.Tag
import com.coffee.api.cafe.infrastructure.persistence.entity.TagEntity
import com.coffee.api.common.infrastructure.persistence.DomainEntityConverter
import org.springframework.stereotype.Component

@Component
class TagConverter : DomainEntityConverter<Tag, TagEntity>(
    Tag::class,
    TagEntity::class
) {
    override fun toDomain(entity: TagEntity): Tag {
        return Tag(
            id = entity.id,
            name = entity.name
        )
    }

    override fun toEntity(domain: Tag): TagEntity {
        return TagEntity(
            id = domain.id.value,
            name = domain.name
        )
    }
}
