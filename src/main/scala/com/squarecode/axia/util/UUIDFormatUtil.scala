package com.squarecode.axia.util

import java.util.UUID
import com.twitter.finagle.axia.exception.ValidationException

/**
  * Author   : Dynastymasra
  * Name     : Dimas Ragil T
  * Email    : dynastymasra@gmail.com
  * LinkedIn : http://www.linkedin.com/in/dynastymasra
  * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
  */
object UUIDFormatUtil {
  implicit class UUIDStringFormat(val str: Option[String]) {
    def toUUIDFormat: UUID = str match {
      case Some(x) => UUID.fromString(x)
      case None => UUID.randomUUID()
      case _ => throw new ValidationException(s"cannot convert $str to UUID")
    }
  }

  implicit class StringUUIDFormat(val s: String) {
    def toUUIDFromString: UUID = s match {
      case x => UUID.fromString(s)
      case _ => throw new ValidationException(s"cannot convert $s to UUID")
    }
  }

  implicit class UUIDToOptStringFormat(val u: UUID) {
    def toOptString: Option[String] = u match {
      case x => Option(x.toString)
      case _ => None
    }
  }
}
