package io.github.wukeji.neatlog.interceptors

import io.github.wukeji.neatlog.core.DomainElementInterceptor
import io.github.wukeji.neatlog.core.replaceTag
import io.github.wukeji.neatlog.domain.DomainElement
import io.github.wukeji.neatlog.elements.LogPrinterElement

class TagPrefixInterceptor(private val tagPrefix: String) : DomainElementInterceptor {

    override fun shouldIntercept(element: DomainElement): Boolean =
        element is LogPrinterElement

    override fun intercept(element: DomainElement): DomainElement {
        if (element is LogPrinterElement) {

            val delegate = element.neatLogPrinter
                .replaceTag { oldTag -> "$tagPrefix$oldTag" }

            return LogPrinterElement(delegate)
        }

        return element
    }
}

class TagSuffixInterceptor(private val tagSuffix: String) : DomainElementInterceptor {

    override fun shouldIntercept(element: DomainElement): Boolean =
        element is LogPrinterElement

    override fun intercept(element: DomainElement): DomainElement {
        if (element is LogPrinterElement) {

            val delegate = element.neatLogPrinter
                .replaceTag { oldTag -> "$oldTag$tagSuffix" }

            return LogPrinterElement(delegate)
        }

        return element
    }
}