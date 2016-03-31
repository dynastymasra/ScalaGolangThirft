package com.squarecode.axia.repository.user

import java.util.UUID

import com.squarecode.axia.entity.UserEntity
import com.websudos.phantom.dsl._

import scala.concurrent.{Future, Await}
import scala.concurrent.duration._

/**
  * Author   : Dynastymasra
  * Name     : Dimas Ragil T
  * Email    : dynastymasra@gmail.com
  * LinkedIn : http://www.linkedin.com/in/dynastymasra
  * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
  */
trait UserRepository extends UserDatabaseProvider {

  def createTable(): Unit = {
    Await.result(database.userModel.createTable(), 5.seconds)
  }

  def getByUserId(id: UUID): Future[Option[UserEntity]] = {
    database.userModel.getByUserId(id)
  }

  def getAllUser(): Future[Seq[UserEntity]] = {
    database.userModel.getAllUser()
  }

  def saveUser(userEntity: UserEntity): Future[ResultSet] = {
    database.userModel.store(userEntity)
  }

  def updateUser(id: UUID, userEntity: UserEntity): Future[ResultSet] = {
    database.userModel.updateUserById(id, userEntity)
  }

  def deleteByUserId(id: UUID): Future[ResultSet] = {
    database.userModel.deleteByUserId(id)
  }
}

object UserRepository extends UserRepository with DefaultUserDatabaseProvider
