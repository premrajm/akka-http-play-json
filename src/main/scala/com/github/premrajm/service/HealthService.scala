package com.github.premrajm.service

import com.github.premrajm.model.{ AggregatedHealth, Health, HealthStatus }

sealed trait HealthService {

  def status: AggregatedHealth = ???
}

class HealthServiceImpl extends HealthService {

  override def status: AggregatedHealth = {
    AggregatedHealth(HealthStatus.UP, Map("dummy" -> Health(HealthStatus.UP)))
  }
}