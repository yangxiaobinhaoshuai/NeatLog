package io.github.wukeji.neatlog.elements

import io.github.wukeji.neatlog.domain.AbsDomainElement
import io.github.wukeji.neatlog.domain.AbsKey
import io.github.wukeji.neatlog.uitlity.Formatter

data class FormatLogElement(val formatter: Formatter) : AbsDomainElement(FormatLogElement) {

    companion object Key : AbsKey<FormatLogElement>()
}
