package com.squarecode.axia.util

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date

/**
  * Author   : Dynastymasra
  * Name     : Dimas Ragil T
  * Email    : dynastymasra@gmail.com
  * LinkedIn : http://www.linkedin.com/in/dynastymasra
  * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
  */
object LocalDateUtil {
  implicit class LocalDateFormat(val o: String) {
    def toLocalDate: LocalDate = LocalDate.parse(o)
  }

  implicit class LocalDateString(val o: LocalDate) {
    def toLocalDateString: String = o.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
  }

  implicit class StringToJavaDate(val s: String) {
    def toStringJavaDate: Date = new SimpleDateFormat("yyyy-MM-dd").parse(s)
  }

  implicit class JavaDateToString(val d: Date) {
    def toJavaDateString: String = new SimpleDateFormat("yyyy-MM-dd").format(d)
  }
}
