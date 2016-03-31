package com.squarecode.axia.domain

import com.squarecode.axia.entity.UserEntity
import com.squarecode.axia.util.LocalDateUtil.StringToJavaDate
import com.squarecode.axia.util.LocalDateUtil.JavaDateToString
import com.twitter.finagle.axia.user.{Gender, Identity}
import com.squarecode.axia.util.LocalDateTimeUtil.{JodaDateTimeFormat, StringJodaTimeFormat}
import com.squarecode.axia.util.UUIDFormatUtil.{UUIDStringFormat, UUIDToOptStringFormat, StringUUIDFormat}

/**
  * Author   : Dynastymasra
  * Name     : Dimas Ragil T
  * Email    : dynastymasra@gmail.com
  * LinkedIn : http://www.linkedin.com/in/dynastymasra
  * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
  */
object UserDomain {
  def ThriftToUserEntity(identity: Identity): UserEntity = {
    UserEntity(identity.id.toUUIDFormat, identity.identityId, identity.firstname, identity.middlename, identity.lastname,
      identity.birthplace, identity.birthdate.toStringJavaDate, identity.gender.name, identity.nationality,
      identity.villageId.toUUIDFromString, identity.address, identity.phone, identity.email, identity.religion, identity.profession,
      identity.active, identity.created.toJodaDateTime, identity.updated.toJodaDateTime)
  }

  def UserEntityToThrift(userEntity: UserEntity): Identity = {
    Identity(userEntity.id.toOptString, userEntity.identityId, userEntity.firstname, userEntity.middlename, userEntity.lastname,
      userEntity.birthplace, userEntity.birthdate.toJavaDateString, Gender.valueOf(userEntity.gender).get, userEntity.nationality,
      userEntity.villageId.toString, userEntity.address, userEntity.phone, userEntity.email, userEntity.religion, userEntity.profession,
      userEntity.active, userEntity.created.toStringJodaTime, userEntity.updated.toStringJodaTime)
  }

  def UserEntitySeqToThrift(userEntity: Seq[UserEntity]): Seq[Identity] = {
    userEntity.map{ i =>
      Identity(i.id.toOptString, i.identityId, i.firstname, i.middlename, i.lastname, i.birthplace,
        i.birthdate.toJavaDateString, Gender.valueOf(i.gender).get, i.nationality, i.villageId.toString, i.address, i.phone,
        i.email, i.religion, i.profession, i.active, i.created.toStringJodaTime, i.updated.toStringJodaTime)
    }
  }
}
