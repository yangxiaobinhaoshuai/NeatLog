package io.github.wukeji.neatlog.interceptors

import io.github.wukeji.neatlog.core.DomainElementInterceptor
import io.github.wukeji.neatlog.domain.DomainElement
import io.github.wukeji.neatlog.elements.LogPrinterElement
import io.github.wukeji.neatlog.core.replaceMessage


class MessagePrefixInterceptor(private val messagePrefix: String) : DomainElementInterceptor {

    override fun shouldIntercept(element: DomainElement): Boolean = element is LogPrinterElement

    override fun intercept(element: DomainElement): DomainElement {
        if (element is LogPrinterElement) {
            val delegate = element
                .neatLogPrinter
                .replaceMessage { oldMessage -> "$messagePrefix$oldMessage" }

            return LogPrinterElement(delegate)
        }
        return element
    }

}

class MessageSuffixInterceptor(private val messageSuffix: String) : DomainElementInterceptor {

    override fun shouldIntercept(element: DomainElement): Boolean = element is LogPrinterElement

    override fun intercept(element: DomainElement): DomainElement {
        if (element is LogPrinterElement) {
            val delegate = element.neatLogPrinter
                .replaceMessage { oldMessage -> "$oldMessage$messageSuffix" }
            return LogPrinterElement(delegate)
        }
        return element
    }

}