package com.squarecode.axia.util

import com.squarecode.axia.entity.UserEntity
import com.twitter.finagle.axia.exception.UserNotFoundException

/**
  * Author   : Dynastymasra
  * Name     : Dimas Ragil T
  * Email    : dynastymasra@gmail.com
  * LinkedIn : http://www.linkedin.com/in/dynastymasra
  * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
  */
object EntityConvertUtil {
  implicit class UserOptToUser(val u: Option[UserEntity]) {
    def toUserFromOpt: UserEntity = u match {
      case Some(x) => x
      case None => throw new UserNotFoundException(s"user not found")
    }
  }
}
