package com.squarecode.axia.entity

import java.util.{UUID, Date}

import org.joda.time.DateTime

/**
  * Author   : Dynastymasra
  * Name     : Dimas Ragil T
  * Email    : dynastymasra@gmail.com
  * LinkedIn : http://www.linkedin.com/in/dynastymasra
  * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
  */
case class UserEntity(id: UUID, identityId: String, firstname: String, middlename: Option[String], lastname: Option[String],
                      birthplace: String, birthdate: Date, gender: String, nationality: String, villageId: UUID, address: String,
                      phone: String, email: Option[String], religion: String, profession: String, active: Boolean, created: DateTime,
                      updated: DateTime)