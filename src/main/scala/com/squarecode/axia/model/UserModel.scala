package com.squarecode.axia.model

import java.util.UUID

import com.datastax.driver.core.Row
import com.squarecode.axia.entity.UserEntity
import com.websudos.phantom.dsl._

import scala.concurrent.Future


/**
  * Author   : Dynastymasra
  * Name     : Dimas Ragil T
  * Email    : dynastymasra@gmail.com
  * LinkedIn : http://www.linkedin.com/in/dynastymasra
  * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
  */
class UserModel extends CassandraTable[ConcreteUserModel, UserEntity] {
  object id extends UUIDColumn(this) with PartitionKey[UUID]
  object identityId extends StringColumn(this) { override lazy val name = "identity_id" }
  object firstname extends StringColumn(this)
  object middlename extends OptionalStringColumn(this)
  object lastname extends OptionalStringColumn(this)
  object birthplace extends StringColumn(this)
  object birthdate extends DateColumn(this)
  object gender extends StringColumn(this)
  object nationality extends StringColumn(this)
  object villageId extends UUIDColumn(this) { override lazy val name = "village_id" }
  object address extends StringColumn(this)
  object phone extends StringColumn(this)
  object email extends OptionalStringColumn(this)
  object religion extends StringColumn(this)
  object profession extends StringColumn(this)
  object active extends BooleanColumn(this)
  object created extends DateTimeColumn(this)
  object updated extends DateTimeColumn(this)

  override def fromRow(r: Row): UserEntity = {
    UserEntity(
      id(r),
      identityId(r),
      firstname(r),
      middlename(r),
      lastname(r),
      birthplace(r),
      birthdate(r),
      gender(r),
      nationality(r),
      villageId(r),
      address(r),
      phone(r),
      email(r),
      religion(r),
      profession(r),
      active(r),
      created(r),
      updated(r)
    )
  }
}

abstract class ConcreteUserModel extends UserModel with RootConnector {

  override def tableName: String = "users"

  def createTable(): Future[ResultSet] = {
    create.ifNotExists().future()
  }

  def store(userEntity: UserEntity): Future[ResultSet] = {
    insert.value(_.id, userEntity.id).value(_.identityId, userEntity.identityId).value(_.firstname, userEntity.firstname)
      .value(_.middlename, userEntity.middlename).value(_.lastname, userEntity.lastname).value(_.birthplace, userEntity.birthplace)
      .value(_.birthdate, userEntity.birthdate).value(_.gender, userEntity.gender).value(_.nationality, userEntity.nationality)
      .value(_.villageId, userEntity.villageId).value(_.address, userEntity.address).value(_.phone, userEntity.phone)
      .value(_.email, userEntity.email).value(_.religion, userEntity.religion).value(_.profession, userEntity.profession)
      .value(_.active, userEntity.active).value(_.created, userEntity.created).value(_.updated, userEntity.updated).future()
  }

  def getByUserId(id: UUID): Future[Option[UserEntity]] = {
    select.where(_.id eqs id).one()
  }

  def getAllUser(): Future[Seq[UserEntity]] = {
    select.fetch()
  }

  def updateUserById(id: UUID, userEntity: UserEntity): Future[ResultSet] = {
    update.where(_.id eqs id).modify(_.identityId setTo userEntity.identityId).and(_.firstname setTo userEntity.firstname)
      .and(_.middlename setTo userEntity.middlename).and(_.lastname setTo userEntity.lastname)
      .and(_.birthplace setTo userEntity.birthplace).and(_.birthdate setTo userEntity.birthdate)
      .and(_.gender setTo userEntity.gender).and(_.nationality setTo userEntity.nationality)
      .and(_.villageId setTo userEntity.villageId).and(_.address setTo userEntity.address).and(_.phone setTo userEntity.phone)
      .and(_.email setTo userEntity.email).and(_.religion setTo userEntity.religion).and(_.profession setTo userEntity.profession)
      .and(_.active setTo userEntity.active).and(_.created setTo userEntity.created).and(_.updated setTo userEntity.updated)
      .future()
  }

  def deleteByUserId(id: UUID): Future[ResultSet] = {
    delete.where(_.id eqs id).future()
  }
}