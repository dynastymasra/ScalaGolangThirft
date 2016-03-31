package com.squarecode.axia

import java.net.InetSocketAddress
import java.util.logging.Logger

import com.twitter.finagle.axia.user.UserService$FinagleClient
import com.twitter.finagle.builder.ClientBuilder
import com.twitter.finagle.thrift.ThriftClientFramedCodec
import org.apache.thrift.protocol.TBinaryProtocol

/**
  * Author   : Dynastymasra
  * Name     : Dimas Ragil T
  * Email    : dynastymasra@gmail.com
  * LinkedIn : http://www.linkedin.com/in/dynastymasra
  * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
  */
object ClientUtilServer {
  def server(address: InetSocketAddress) = {

    val serve = ClientBuilder()
      .hosts(address)
      .codec(ThriftClientFramedCodec())
      .hostConnectionLimit(1)
      .retries(3)
      .logger(Logger.getLogger("http"))
      .build()
    new UserService$FinagleClient(serve, new TBinaryProtocol.Factory())
  }
}
