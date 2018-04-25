package com.github.premrajm.route

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.{ delete, get, post }
import akka.http.scaladsl.server.directives.PathDirectives.path
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import com.github.premrajm.model.User
import com.github.premrajm.service.{ UserService, UserServiceImpl }
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport

//#user-routes-class
trait UserRoutes extends PlayJsonSupport {
  //#user-routes-class

  // we leave these abstract, since they will be provided by the App
  implicit def system: ActorSystem

  lazy val userRoutesLog = Logging(system, classOf[UserRoutes])

  // other dependencies that UserRoutes use
  def service: UserService = new UserServiceImpl

  //#all-routes
  //#users-get-post
  //#users-get-delete
  lazy val userRoutes: Route =
    pathPrefix("users") {
      concat(
        //#users-get-delete
        pathEnd {
          concat(
            get {
              complete(service.getAll)
            },
            post {
              entity(as[User]) { user =>
                userRoutesLog.info("Created user [{}]", user)
                complete(StatusCodes.Created, service.add(user))
              }
            }
          )
        },
        //#users-get-post
        //#users-get-delete
        path(Segment) { id =>
          concat(
            get {
              complete(service.find(id))
            },
            delete {
              userRoutesLog.info("Deleted user [{}]", id)
              complete(StatusCodes.OK, service.delete(id))
            }
          )
        }
      )
      //#users-get-delete
    }

  //#all-routes
}
