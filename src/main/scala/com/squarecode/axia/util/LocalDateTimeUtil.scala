package com.squarecode.axia.util

import java.time.LocalDateTime
import org.joda.time.format.{DateTimeFormat => joda}
import java.time.format.DateTimeFormatter
import org.joda.time.DateTime

/**
  * Author   : Dynastymasra
  * Name     : Dimas Ragil T
  * Email    : dynastymasra@gmail.com
  * LinkedIn : http://www.linkedin.com/in/dynastymasra
  * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
  */
object LocalDateTimeUtil {
  implicit class LocalDateTimeFormat(val o: String) {
    def toLocalDateTime: LocalDateTime = LocalDateTime.parse(o, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
  }

  implicit class JodaDateTimeFormat(val d: String) {
    def toJodaDateTime: DateTime = DateTime.parse(d, joda.forPattern("yyyy-MM-dd'T'HH:mm:ss"))
  }

  implicit class StringJodaTimeFormat(val d: DateTime) {
    def toStringJodaTime: String = d.toString("yyyy-MM-dd'T'HH:mm:ss")
  }

  implicit class LocalDateTimeString(val o: LocalDateTime) {
    def toLocalDateTimeString: String = o.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"))
  }
}
