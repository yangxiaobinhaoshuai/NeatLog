package io.github.wukeji.neatlog.elements

import io.github.wukeji.neatlog.domain.AbsKey
import io.github.wukeji.neatlog.domain.AbsDomainElement
import io.github.wukeji.neatlog.core.NeatLogLevel

data class LogLevelThresholdElement(val level: NeatLogLevel) :
    AbsDomainElement(LogLevelThresholdElement) {
    companion object Key : AbsKey<LogLevelThresholdElement>()
}
