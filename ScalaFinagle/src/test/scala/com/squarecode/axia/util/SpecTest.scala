package com.squarecode.axia.util

import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{OptionValues, Inspectors, FlatSpec, Matchers}

/**
  * Author   : Dynastymasra
  * Name     : Dimas Ragil T
  * Email    : dynastymasra@gmail.com
  * LinkedIn : http://www.linkedin.com/in/dynastymasra
  * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
  */
abstract class SpecTest extends FlatSpec with Matchers with Inspectors with ScalaFutures with OptionValues
