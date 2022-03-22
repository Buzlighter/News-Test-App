package com.test.newsapp.util

class DatePattern {
    companion object {
        fun editDate(date: String?): String {
            return date?.substring(0, 10) ?: ""
        }
    }
}