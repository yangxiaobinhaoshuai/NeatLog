package io.github.wukeji.neatlog.elements

import io.github.wukeji.neatlog.domain.AbsKey
import io.github.wukeji.neatlog.domain.AbsDomainElement

data class EnableElement(val enable: Boolean) : AbsDomainElement(EnableElement) {
    companion object Key : AbsKey<EnableElement>()
}
