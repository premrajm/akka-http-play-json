package com.github.premrajm.model

import enumeratum.{ Enum, EnumEntry, PlayJsonEnum }
import play.api.libs.json.{ Json, OWrites, Reads }

sealed trait HealthStatus extends EnumEntry

object HealthStatus extends Enum[HealthStatus] with PlayJsonEnum[HealthStatus] {

  val values = findValues

  case object UP extends HealthStatus

  case object DOWN extends HealthStatus

}

final case class Health(status: HealthStatus, details: Option[String] = Option.empty)

final case class AggregatedHealth(status: HealthStatus, dependencies: Map[String, Health])

object Health {
  implicit val healthRead: Reads[Health] = Json.reads[Health]
  implicit val healthWrites: OWrites[Health] = Json.writes[Health]
}

object AggregatedHealth {
  implicit val aggregatedHealthRead: Reads[AggregatedHealth] = Json.reads[AggregatedHealth]
  implicit val aggregatedHealthWrites: OWrites[AggregatedHealth] = Json.writes[AggregatedHealth]
}
