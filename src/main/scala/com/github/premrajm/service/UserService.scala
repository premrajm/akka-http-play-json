package com.github.premrajm.service

import com.github.premrajm.model.{ RegularUser, User, Users }

import scala.util.Random

sealed trait UserService {

  def find(id: String): Option[RegularUser] = ???

  def getAll: Users = ???

  def add(user: RegularUser): RegularUser = ???

  def delete(id: String): Option[RegularUser] = ???

}

class UserServiceImpl extends UserService {

  private val users = List(
    RegularUser("100", "Marcus Cruz")
  )

  private def id: String = Random.alphanumeric take 16 mkString

  private val usersMap = scala.collection.mutable.Map[String, RegularUser]() ++= (users map (u => u.id -> u) toMap)

  override def find(id: String): Option[RegularUser] = usersMap.get(id)

  override def add(user: RegularUser): RegularUser = {
    val saved = RegularUser(id, user.name)
    usersMap += saved.id -> saved
    saved
  }

  override def getAll: Users = Users(usersMap.values.toSeq)

  override def delete(id: String): Option[RegularUser] = usersMap remove id
}