package com.coffee.api.cafe.infrastructure.persistence.entity

import com.coffee.api.cafe.domain.CafeArea
import com.coffee.api.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "cafes")
class CafeEntity(
    id: UUID,
    reasonForSelection: String,
    naverMapUrl: String,
    name: String,
    nearestStation: String,
    location: String,
    price: Int,
    previewImages: List<String>,
    mainImages: List<String>,
    area: CafeArea,
) : BaseEntity() {

    @Id
    var id: UUID = id
        protected set

    @Column(nullable = false)
    var reasonForSelection: String = reasonForSelection
        protected set

    @Column(nullable = false)
    var naverMapUrl: String = naverMapUrl
        protected set

    @Column(nullable = false)
    var name: String = name
        protected set

    var nearestStation: String = nearestStation
        protected set

    var location: String = location
        protected set

    var price: Int = price
        protected set

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "cafe_preview_images",
        joinColumns = [JoinColumn(name = "cafe_id")],
        foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT),
    )
    var previewImages: List<String> = previewImages
        protected set

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "cafe_main_images",
        joinColumns = [JoinColumn(name = "cafe_id")],
        foreignKey = ForeignKey(value = ConstraintMode.NO_CONSTRAINT),
    )
    var mainImages: List<String> = mainImages
        protected set

    @Enumerated(EnumType.STRING)
    @Column(name = "area", nullable = false)
    var area: CafeArea = area
        protected set
}
