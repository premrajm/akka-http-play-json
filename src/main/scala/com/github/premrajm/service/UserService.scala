package com.github.premrajm.service

import play.api.libs.json.{Json, OWrites, Reads}

import scala.util.Random

sealed trait UserService {

  def find(id: String): Option[User] = ???

  def getAll: Users = ???

  def add(user: User): User = ???

  def delete(id: String): Option[User] = ???

}

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

class UserServiceImpl extends UserService {

  private val users = List(
    User("100", "Marcus Cruz", "Austria")
  )

  private def id: String = Random.alphanumeric take 16 mkString

  private val usersMap = scala.collection.mutable.Map[String, User]() ++= (users map (u => u.id -> u) toMap)

  override def find(id: String): Option[User] = usersMap.get(id)

  override def add(user: User): User = {
    val saved = User(id, user.name, user.countryOfResidence)
    usersMap += saved.id -> saved
    saved
  }

  override def getAll: Users = Users(usersMap.values.toSeq)

  override def delete(id: String): Option[User] = usersMap remove id
}