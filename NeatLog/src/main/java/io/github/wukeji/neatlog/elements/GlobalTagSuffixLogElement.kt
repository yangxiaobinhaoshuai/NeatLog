package io.github.wukeji.neatlog.elements

import io.github.wukeji.neatlog.domain.AbsKey
import io.github.wukeji.neatlog.domain.AbsDomainElement

data class GlobalTagSuffixLogElement(val tagSuffix: String) : AbsDomainElement(GlobalTagSuffixLogElement) {

    companion object Key : AbsKey<GlobalTagSuffixLogElement>()
}
