package com.squarecode.axia.repository

import java.util.UUID

import com.squarecode.axia.entity.UserEntity
import com.squarecode.axia.repository.user.UserRepository
import com.squarecode.axia.util.SpecTest
import com.squarecode.axia.util.LocalDateUtil.StringToJavaDate
import com.squarecode.axia.util.LocalDateTimeUtil.JodaDateTimeFormat
import com.twitter.finagle.axia.user.Gender
import com.typesafe.scalalogging.Logger
import com.websudos.util.testing.{Sample, _}
import org.junit.runner.RunWith
import org.scalatest.BeforeAndAfterAll
import org.scalatest.junit.JUnitRunner
import org.slf4j.LoggerFactory
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Author   : Dynastymasra
  * Name     : Dimas Ragil T
  * Email    : dynastymasra@gmail.com
  * LinkedIn : http://www.linkedin.com/in/dynastymasra
  * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
  */
@RunWith(classOf[JUnitRunner])
class UserRepositoryTest extends SpecTest with BeforeAndAfterAll {

  val logger = Logger(LoggerFactory.getLogger(this.getClass))

  override def beforeAll(): Unit = {
    logger.info("Create table user if not exist")
    UserRepository.createTable()
  }

  implicit object UserGenerator extends Sample[UserEntity] {
    override def sample: UserEntity = {
      UserEntity(gen[UUID], gen[String], gen[FirstName].value, Some(gen[LastName].value), Some(gen[LastName].value), gen[City].value,
        "2016-03-12".toStringJavaDate, Gender.Male.name, gen[Country].value, gen[UUID], gen[LoremIpsum].value, gen[String],
        Some(gen[EmailAddress].value), gen[String], gen[String], true, "2016-12-13 22:45:34".toJodaDateTime, "2016-12-13 22:45:34".toJodaDateTime)
    }
  }

  "A user" should "be inserted into cassandra" in {
    val sample = gen[UserEntity]
    val future = UserRepository.saveUser(sample)

    logger.info(s"result test insert user : $future" )

    whenReady(future) { result =>
      result isExhausted() shouldBe true
      result wasApplied() shouldBe true
      UserRepository.deleteByUserId(sample.id)
    }
  }

  "A user" should "find user by id" in {
    val sample = gen[UserEntity]

    val chain = for {
      store <- UserRepository.saveUser(sample)
      get <- UserRepository.getByUserId(sample.id)
    } yield get

    logger.info(s"result user test get user : $chain")

    whenReady(chain) { res =>
      res shouldBe defined
      UserRepository.deleteByUserId(sample.id)
    }
  }

  "A user" should "update user data" in {
    val sample = gen[UserEntity]
    val update = gen[UserEntity]

    logger.info(s"User update sample : $sample")
    logger.info(s"User data for update : $update")

    val chain = for {
      store <- UserRepository.saveUser(sample)
      get <- UserRepository.getByUserId(sample.id)
      update <- UserRepository.updateUser(sample.id, update)
      getUpdate <- UserRepository.getByUserId(sample.id)
    } yield (get, getUpdate)

    whenReady(chain) { case (initial, modified) =>
      initial shouldBe defined
      initial.value.firstname shouldEqual sample.firstname

      modified shouldBe defined
      modified.value.firstname shouldEqual update.firstname

      UserRepository.deleteByUserId(sample.id)
    }
  }

  "A user" should "get all data user" in {
    val sample1 = gen[UserEntity]
    val sample2 = gen[UserEntity]
    val sample3 = gen[UserEntity]

    val store = for {
      s1 <- UserRepository.saveUser(sample1)
      s2 <- UserRepository.saveUser(sample2)
      s3 <- UserRepository.saveUser(sample3)
    } yield (s1, s2, s3)

    whenReady(store) { insert =>
      val getAll = UserRepository.getAllUser()
      whenReady(getAll) { get =>
        get shouldBe a [List[_]]
        UserRepository.deleteByUserId(sample1.id)
        UserRepository.deleteByUserId(sample2.id)
        UserRepository.deleteByUserId(sample3.id)
      }
    }
  }

  "A user" should "delete user by id" in {
    val sample = gen[UserEntity]

    val chain = for {
      store <- UserRepository.saveUser(sample)
      delete <- UserRepository.deleteByUserId(sample.id)
    } yield delete

    whenReady(chain) { result =>
      result isExhausted() shouldBe true
      result wasApplied() shouldBe true
    }
  }
}
