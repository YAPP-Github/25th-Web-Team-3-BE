package com.coffee.api.cafe.infrastructure.persistence.entity

import com.coffee.api.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.*
import java.util.*

@Entity
@Table(
    name = "cafe_recommend_group_mappings",
    indexes = [
        Index(name = "idx_cafe_recommend_group_mapping_cafe_id", columnList = "cafe_id"),
        Index(name = "idx_cafe_recommend_group_mapping_group_id", columnList = "group_id")
    ]
)
class CafeRecommendGroupMappingEntity(
    @Id
    val id: UUID,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var cafe: CafeEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id", foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT))
    var recommendGroup: CafeRecommendGroupEntity

) : BaseEntity()
