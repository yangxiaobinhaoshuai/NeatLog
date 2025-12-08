package io.github.wukeji.neatlog.elements

import io.github.wukeji.neatlog.domain.AbsDomainElement
import io.github.wukeji.neatlog.domain.AbsKey
import io.github.wukeji.neatlog.uitlity.LogPrinter

open class LogPrinterDelegate(val printer: LogPrinter) : LogPrinter by printer

data class LogPrinterLogElement(val logPrinter: LogPrinter) :
    AbsDomainElement(LogPrinterLogElement) {

    companion object Key : AbsKey<LogPrinterLogElement>()

}
