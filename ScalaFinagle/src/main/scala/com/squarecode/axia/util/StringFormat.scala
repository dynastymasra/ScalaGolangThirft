package com.squarecode.axia.util

/**
  * Author   : Dynastymasra
  * Name     : Dimas Ragil T
  * Email    : dynastymasra@gmail.com
  * LinkedIn : http://www.linkedin.com/in/dynastymasra
  * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
  */
object StringFormat {
  implicit class OptionFormat(val str: Option[String]) {
    def toStr: String = str match {
      case Some(x) => x
      case None => ""
    }
  }
}
