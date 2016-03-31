package com.squarecode.axia.repository

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.Logger
import com.websudos.phantom.connectors.ContactPoints
import org.slf4j.LoggerFactory

import scala.collection.JavaConversions._

/**
  * Author   : Dynastymasra
  * Name     : Dimas Ragil T
  * Email    : dynastymasra@gmail.com
  * LinkedIn : http://www.linkedin.com/in/dynastymasra
  * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
  */
object Connector {
  val logger = Logger(LoggerFactory.getLogger(this.getClass))

  val config = ConfigFactory.load("application.conf").getConfig("cassandra")
  val hosts = config.getStringList("host")
  val port = config.getInt("port")
  val username = config.getString("username")
  val password = config.getString("password")
  val keyspace = config.getString("keyspace")

  if (hosts.length < 1) {
    hosts.add("localhost")
  }

  logger.info(s"Cassandra host:$hosts port:$port keyspace:$keyspace")
  println(hosts)

  val connector = ContactPoints(hosts, port).withClusterBuilder(
    _.withCredentials(username, password)
  ).keySpace(keyspace)
}
