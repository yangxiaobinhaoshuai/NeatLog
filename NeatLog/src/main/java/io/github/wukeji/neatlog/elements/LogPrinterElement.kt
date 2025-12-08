package io.github.wukeji.neatlog.elements

import io.github.wukeji.neatlog.core.NeatLogPrinter
import io.github.wukeji.neatlog.domain.AbsDomainElement
import io.github.wukeji.neatlog.domain.AbsKey

data class LogPrinterElement(val neatLogPrinter: NeatLogPrinter) :
    AbsDomainElement(LogPrinterElement) {
    companion object Key : AbsKey<LogPrinterElement>()
}
