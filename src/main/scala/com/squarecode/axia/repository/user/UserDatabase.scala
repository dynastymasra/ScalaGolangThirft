package com.squarecode.axia.repository.user

import com.squarecode.axia.model.ConcreteUserModel
import com.squarecode.axia.repository.Connector
import com.websudos.phantom.db.DatabaseImpl
import com.websudos.phantom.dsl.KeySpaceDef


/**
  * Author   : Dynastymasra
  * Name     : Dimas Ragil T
  * Email    : dynastymasra@gmail.com
  * LinkedIn : http://www.linkedin.com/in/dynastymasra
  * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
  */
class UserDatabase(override val connector: KeySpaceDef) extends DatabaseImpl(connector) {
  object userModel extends ConcreteUserModel with connector.Connector
}

object UserDefault extends UserDatabase(Connector.connector)

trait UserDatabaseProvider {
  def database: UserDatabase
}

trait DefaultUserDatabaseProvider extends UserDatabaseProvider {
  override val database = UserDefault
}
