package com.coffee.api.cafe.infrastructure.repository

import com.coffee.mysql.support.DomainEntityConverter

class CafeConverter : DomainEntityConverter<Cafe, CafeEntity>(
    Cafe::class,
    CafeEntity::class,
) {
    
}