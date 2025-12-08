package io.github.wukeji.neatlog.elements

import io.github.wukeji.neatlog.domain.AbsKey
import io.github.wukeji.neatlog.domain.AbsDomainElement
import io.github.wukeji.neatlog.core.LogLevel

data class LogLevelLogElement(val level: LogLevel) : AbsDomainElement(LogLevelLogElement) {

    companion object Key : AbsKey<LogLevelLogElement>()

}
