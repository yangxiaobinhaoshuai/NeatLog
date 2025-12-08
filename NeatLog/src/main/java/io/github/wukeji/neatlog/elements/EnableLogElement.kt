package io.github.wukeji.neatlog.elements

import io.github.wukeji.neatlog.domain.AbsKey
import io.github.wukeji.neatlog.domain.AbsDomainElement

data class EnableLogElement(val enable: Boolean) : AbsDomainElement(EnableLogElement) {

    companion object Key : AbsKey<EnableLogElement>()

}
