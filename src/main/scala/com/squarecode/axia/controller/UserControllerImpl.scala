package com.squarecode.axia.controller

import com.squarecode.axia.domain.UserDomain
import com.squarecode.axia.repository.user.UserRepository
import com.twitter.finagle.axia.user.{Identity, UserService}
import com.twitter.finagle.axia.exception.{ValidationException, CommonException, UserNotFoundException}
import com.squarecode.axia.util.ScalaTwitterUtil
import com.squarecode.axia.util.UUIDFormatUtil.StringUUIDFormat
import com.twitter.util.{Await, Future}
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory
import com.squarecode.axia.util.EntityConvertUtil.UserOptToUser
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Author   : Dynastymasra
  * Name     : Dimas Ragil T
  * Email    : dynastymasra@gmail.com
  * LinkedIn : http://www.linkedin.com/in/dynastymasra
  * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
  */
class UserControllerImpl extends UserService.FutureIface {
  val logger = Logger(LoggerFactory.getLogger(this.getClass))

  override def ping(): Future[String] = {
    logger.info("Request user ping...")
    Future.value("PONG!!!")
  }

  override def create(identity: Identity): Future[Identity] = {
    logger.info("Request user create...")

    try {
      val user = UserDomain.ThriftToUserEntity(identity)
      Await.result(ScalaTwitterUtil.scalaToTwitterFuture(UserRepository.saveUser(user)))

      val result = UserRepository.getByUserId(user.id).map { result =>
        UserDomain.UserEntityToThrift(result.toUserFromOpt)
      }
      ScalaTwitterUtil.scalaToTwitterFuture(result)
    } catch {
      case e: ValidationException => Future.exception(ValidationException(e.description))
      case e: Throwable => Future.exception(CommonException(e.getMessage))
    }
  }

  override def update(id: String, identity: Identity): Future[Identity] = {
    logger.info("Request user update...")

    try {
      val user = UserDomain.ThriftToUserEntity(identity)

      Await.result(ScalaTwitterUtil.scalaToTwitterFuture(UserRepository.updateUser(id.toUUIDFromString, user)))

      val result = UserRepository.getByUserId(id.toUUIDFromString).map { result =>
        UserDomain.UserEntityToThrift(result.toUserFromOpt)
      }
      ScalaTwitterUtil.scalaToTwitterFuture(result)
    } catch {
      case e: ValidationException => Future.exception(ValidationException(e.description))
      case e: Throwable => Future.exception(CommonException(e.getMessage))
    }
  }

  override def get(id: String): Future[Identity] = {
    logger.info(s"Request get user by id $id")

    try {
      val result = UserRepository.getByUserId(id.toUUIDFromString).map { result =>
        UserDomain.UserEntityToThrift(result.toUserFromOpt)
      }
      ScalaTwitterUtil.scalaToTwitterFuture(result)
    } catch {
      case e: UserNotFoundException => Future.exception(UserNotFoundException(s"user not found with id $id"))
      case e: Throwable => Future.exception(CommonException(e.getMessage))
    }
  }

  override def remove(id: String): Future[Unit] = {
    logger.info(s"Request delete user id $id")

    try {
      UserRepository.deleteByUserId(id.toUUIDFromString)
    } catch {
      case e: ValidationException => Future.exception(ValidationException(e.description))
      case e: Throwable => Future.exception(CommonException(e.getMessage))
    }
    Future.value()
  }

  override def getAll(): Future[Seq[Identity]] = {
    logger.info("Request get all user")
    try {
      val result = UserRepository.getAllUser().map { result =>
        UserDomain.UserEntitySeqToThrift(result)
      }
      ScalaTwitterUtil.scalaToTwitterFuture(result)
    } catch {
      case e: Throwable => Future.exception(CommonException(e.getMessage))
    }
  }
}