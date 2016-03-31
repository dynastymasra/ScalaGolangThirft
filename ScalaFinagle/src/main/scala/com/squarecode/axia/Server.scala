package com.squarecode.axia

import java.net.InetSocketAddress
import com.squarecode.axia.repository.user.UserRepository
import com.twitter.finagle.axia.user.UserService
import com.squarecode.axia.controller.UserControllerImpl
import com.twitter.finagle.builder.ServerBuilder
import com.twitter.finagle.thrift.ThriftServerFramedCodec
import org.apache.thrift.protocol.TBinaryProtocol
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

/**
  * Author   : Dynastymasra
  * Name     : Dimas Ragil T
  * Email    : dynastymasra@gmail.com
  * LinkedIn : http://www.linkedin.com/in/dynastymasra
  * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
  */
object Server extends App {
  val logger = Logger(LoggerFactory.getLogger(this.getClass))

  val config = ConfigFactory.load("application.conf").getConfig("app")
  val host = config.getString("host")
  val port = config.getInt("port")

  logger.info(s"Start server user host:$host port:$port")
  serve(new InetSocketAddress(host, port))
  logger.info("Server user running...")

  def serve(address: InetSocketAddress) = {
    UserRepository.createTable()

    val service = new UserService.FinagledService(new UserControllerImpl, new TBinaryProtocol.Factory())

    ServerBuilder()
      .keepAlive(true)
      .logChannelActivity(true)
      .codec(ThriftServerFramedCodec())
      .bindTo(address)
      .name("User")
      .build(service)
  }
}

