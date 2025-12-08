package io.github.wukeji.neatlog.core

import io.github.wukeji.neatlog.domain.DomainElement

interface DomainElementInterceptor {

    fun shouldIntercept(element: DomainElement): Boolean

    fun intercept(element: DomainElement): DomainElement

    @Suppress("UnnecessaryVariable")
    operator fun plus(other: DomainElementInterceptor): DomainElementInterceptor {
        val first = this
        val second = other

        return object : DomainElementInterceptor {

            override fun shouldIntercept(element: DomainElement): Boolean =
                first.shouldIntercept(element) || second.shouldIntercept(element)

            override fun intercept(element: DomainElement): DomainElement {
                val afterFirst = if (first.shouldIntercept(element)) first.intercept(element) else element

                return if (second.shouldIntercept(afterFirst)) {
                    second.intercept(afterFirst)
                } else {
                    afterFirst
                }
            }


        }
    }

}