package com.squarecode.axia.value

/**
  * Author   : Dynastymasra
  * Name     : Dimas Ragil T
  * Email    : dynastymasra@gmail.com
  * LinkedIn : http://www.linkedin.com/in/dynastymasra
  * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
  */
sealed abstract class GenderType(val gender: String) {
  override def toString: String = gender

  def unapply(genderType: GenderType) : Option[String] = {
    Some(genderType.gender)
  }
}

case object Male extends GenderType("MALE")
case object Female extends GenderType("FEMALE")

object GenderType {
  def apply(gender: String) = {
    gender.toUpperCase match {
      case "MALE" => Male
      case "FEMALE" => Female
      case _ => throw new IllegalArgumentException(s"Gender type $gender not known")
    }
  }
}
