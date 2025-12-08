package io.github.wukeji.neatlog.elements

import io.github.wukeji.neatlog.core.DomainElementInterceptor
import io.github.wukeji.neatlog.domain.AbsDomainElement
import io.github.wukeji.neatlog.domain.AbsKey

data class InterceptorElement(val interceptor: DomainElementInterceptor) :
    AbsDomainElement(InterceptorElement) {
    companion object Key : AbsKey<InterceptorElement>()
}
