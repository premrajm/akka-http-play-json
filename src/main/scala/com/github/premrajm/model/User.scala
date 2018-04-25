package com.github.premrajm.model

import play.api.libs.json.{ Json, OWrites, Reads }

//#user-case-classes
final case class User(id: String, name: String, countryOfResidence: String)

final case class Users(users: Seq[User])

object User {
  implicit val userRead: Reads[User] = Json.reads[User]
  implicit val userWrites: OWrites[User] = Json.writes[User]
}

object Users {
  implicit val usersRead: Reads[Users] = Json.reads[Users]
  implicit val usersWrites: OWrites[Users] = Json.writes[Users]
}

//#user-case-classes