package com.coffee.api.cafe.infrastructure.persistence.entity

import com.coffee.api.common.infrastructure.persistence.BaseEntity
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "cafes")
class CafeEntity(
    @Id
    var id: UUID,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "nearest_station", nullable = false)
    var nearestStation: String,

    @Column(name = "location", nullable = false)
    var location: String,

    @Column(name = "price", nullable = false)
    var price: Int,

    @Column(name = "preview_images")
    var previewImages: String,

    @Column(name = "main_images")
    var mainImages: String,
) : BaseEntity() {

    @OneToMany
    var cafeTags: MutableList<CafeTagEntity> = mutableListOf()
        protected set

    @OneToMany
    var coffeeBeans: MutableList<CoffeeBeanEntity> = mutableListOf()
        protected set

    @OneToMany
    var menus: MutableList<MenuEntity> = mutableListOf()
        protected set
}