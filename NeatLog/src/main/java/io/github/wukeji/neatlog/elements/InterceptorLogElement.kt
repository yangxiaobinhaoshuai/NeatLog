package io.github.wukeji.neatlog.elements

import io.github.wukeji.neatlog.uitlity.DomainElementInterceptor
import io.github.wukeji.neatlog.domain.AbsDomainElement
import io.github.wukeji.neatlog.domain.AbsKey

data class InterceptorLogElement(val interceptor: DomainElementInterceptor) : AbsDomainElement(InterceptorLogElement) {

    companion object Key : AbsKey<InterceptorLogElement>()
}
