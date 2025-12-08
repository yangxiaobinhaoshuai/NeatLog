package io.github.wukeji.neatlog.elements

import io.github.wukeji.neatlog.domain.AbsKey
import io.github.wukeji.neatlog.domain.AbsDomainElement

data class GlobalTagPrefixLogElement(val tagPrefix: String) : AbsDomainElement(GlobalTagPrefixLogElement) {

    companion object Key : AbsKey<GlobalTagPrefixLogElement>()
}
