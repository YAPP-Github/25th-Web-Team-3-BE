package com.coffee.api.common.aop

import io.micrometer.core.instrument.MeterRegistry
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component

@Aspect
@Component
class PageViewAspect(
    private val meterRegistry: MeterRegistry
) {

    @Before("@annotation(pageViewed)")
    fun countPageView(joinPoint: JoinPoint, pageViewed: PageViewed) {
        val counterName = "page.view.count"
        val tagName = pageViewed.name

        meterRegistry
            .counter(counterName, "page", tagName)
            .increment()
    }
}
