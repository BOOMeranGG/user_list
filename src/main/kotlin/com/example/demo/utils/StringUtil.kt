package com.example.demo.utils

object StringUtil {

    /**
     * Очищает телефон от лишних символов
     */
    fun sanitizePhone(phone: String): String {
        if (phone.isBlank())
            return ""

        val result = StringBuilder()
        val phoneTemp = phone.trim { it <= ' ' }
        if (phoneTemp.startsWith("+")) {
            result.append("+")
        }
        result.append(phoneTemp.replace("\\D+".toRegex(), ""))

        return result.toString()
    }

    /**
     * Приводит предварительно валидированный телефон к единому стандарту вида +79161234567
     */
    fun phoneRusStandardization(phone: String): String {
        if (phone.isBlank())
            return ""

        return when (phone.length) {
            11 -> if (phone.startsWith("7") || phone.startsWith("8")) {
                "+7" + phone.substring(1)
            } else {
                phone
            }
            10 -> if (phone.startsWith("+")) {
                phone
            } else {
                "+7$phone"
            }
            else -> if (phone.length in 8..16 && phone.startsWith("+")) {
                phone
            } else {
                ""
            }
        }
    }
}