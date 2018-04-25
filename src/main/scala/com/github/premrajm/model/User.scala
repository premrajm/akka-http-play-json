package com.github.premrajm.model

import ai.x.play.json.Jsonx
import enumeratum.{ Enum, EnumEntry, PlayJsonEnum }
import play.api.libs.json._

sealed trait Role extends EnumEntry

object Role extends Enum[Role] with PlayJsonEnum[Role] {

  val values = findValues

  case object ADMIN extends Role

  case object USER extends Role

}

sealed trait User {
  def id: String = ???

  def name: String = ???

  def role: Role = ???
}

final case class AdminUser(override val id: String, override val name: String, override val role: Role = Role.ADMIN) extends User

final case class RegularUser(override val id: String, override val name: String, override val role: Role = Role.USER) extends User

final case class Users(users: Seq[User])

object User {
  implicit lazy val jsonFormat: Format[User] = Jsonx.formatSealed[User]
}

object AdminUser {
  implicit lazy val jsonFormat: Format[AdminUser] = Jsonx.formatCaseClass[AdminUser]
}

object RegularUser {
  implicit lazy val jsonFormat: Format[RegularUser] = Jsonx.formatCaseClass[RegularUser]
}

object Users {
  implicit lazy val jsonFormat: Format[Users] = Jsonx.formatCaseClass[Users]
}

