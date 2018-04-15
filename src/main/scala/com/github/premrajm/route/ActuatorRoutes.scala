package com.github.premrajm.route

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import com.github.premrajm.service.{ HealthService, HealthServiceImpl }
import de.heikoseeberger.akkahttpplayjson.PlayJsonSupport

trait ActuatorRoutes extends PlayJsonSupport {

  implicit def system: ActorSystem

  lazy val actuatorRoutesLog = Logging(system, classOf[ActuatorRoutes])

  def healthService: HealthService = new HealthServiceImpl

  //#all-routes
  lazy val healthRoutes: Route =
    concat(
      path("health") {
        get {
          complete(healthService.status)
        }
      },
      path("info") {
        get {
          val info = Map(
            "name" -> "akka http play json",
            "description" -> "Play json examples with akka http",
            "version" -> "1.0.1"
          )
          complete(info)
        }
      }
    )
}
