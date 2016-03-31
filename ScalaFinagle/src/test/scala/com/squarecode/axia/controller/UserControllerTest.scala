package com.squarecode.axia.controller

import java.net.InetSocketAddress

import com.squarecode.axia.{ClientUtilServer, Server}
import com.twitter.finagle.axia.user.{Gender, Identity}
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.Logger
import org.joda.time.DateTime
import org.junit.runner.RunWith
import com.websudos.util.testing.{Sample, _}
import org.scalatest.junit.JUnitRunner
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpec}
import org.slf4j.LoggerFactory

/**
  * Author   : Dynastymasra
  * Name     : Dimas Ragil T
  * Email    : dynastymasra@gmail.com
  * LinkedIn : http://www.linkedin.com/in/dynastymasra
  * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
  */
@RunWith(classOf[JUnitRunner])
class UserControllerTest extends WordSpec with MustMatchers with BeforeAndAfterAll{
  val logger = Logger(LoggerFactory.getLogger(this.getClass))

  val config = ConfigFactory.load("app.conf")
  val url = config.getConfig("app")

  val host = url.getString("host")
  val port = url.getInt("port")

  val server = Server.serve(new InetSocketAddress(host, port))
  val client = ClientUtilServer.server(new InetSocketAddress(host, port))

  override protected def afterAll(): Unit = {
    server.close()
  }

  implicit object UserGenerator extends Sample[Identity] {
    override def sample: Identity = {
      Identity(Some("a1fdf1cf-36b6-4960-8e24-1db6d63980fd"), gen[String], gen[FirstName].value, Some(gen[LastName].value),
        Some(gen[LastName].value), gen[String], "2015-11-11", Gender.Male, gen[Country].value, gen[String],
        gen[CountryCode].value, gen[String], Some(gen[EmailAddress].value), gen[String], gen[String], true,
        gen[DateTime].toString("yyyy-MM-dd HH:mm:ss"), gen[DateTime].toString("yyyy-MM-dd HH:mm:ss"))
    }
  }

  "User ping service" should {
    "Serve server and client" in {
      val result = client.ping()
      result.get()

      result onSuccess { reply =>
        reply must be("PONG!!!")
      } onFailure { res =>
        logger.error("Error test user ping")
      }
    }
  }

  "User create service" should {
    "Serve server and client" in {
      val sample = gen[Identity]

      val result = client.create(sample)
      result.get()

      result onSuccess { reply =>
        reply must be(sample)
        reply.id must be(sample.id)
      } onFailure { res =>
        logger.error("Error test user create")
      }
    }
  }

  "User update service" should {
    "Serve server and client" in {
      val sample = gen[Identity]
      val sample2 = gen[Identity]

      client.create(sample)
      val result = client.update(sample.id.get, sample2)
      result.get()

      result onSuccess { reply =>
        reply must be(sample)
        reply.firstname must be(sample2.firstname)
      } onFailure { res =>
        logger.error("Error test user update")
      }
    }
  }

  "User getAll service" should {
    "Serve server and client" in {
      val sample = gen[Identity]

      client.create(sample)
      val result = client.getAll()
      result.get()

      result onSuccess { reply =>
        reply must have length 1
      } onFailure { res =>
        logger.error("Error test user getAll")
      }
    }
  }

  "User get service" should {
    "Serve server and client" in {
      val sample = gen[Identity]

      client.create(sample)
      val result = client.get(sample.id.get)
      result.get()

      result onSuccess { reply =>
        reply.firstname must be(sample.firstname)
      } onFailure { res =>
        logger.error("Error test user get")
      }
    }
  }

  "User delete service" should {
    "Server server and client" in {
      val result = client.remove("a1fdf1cf-36b6-4960-8e24-1db6d63980fd")
      result.get()

      result onFailure { res =>
        logger.error("Error test delete user")
      }
    }
  }
}
