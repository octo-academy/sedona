package com.github.octoacademy.sedona.configuration

import eu.timepit.refined.api.Refined
import eu.timepit.refined.collection.NonEmpty

case class Database(
  className: Refined[String, NonEmpty],
  url: Refined[String, NonEmpty],
  user: Refined[String, NonEmpty],
  password: Refined[String, NonEmpty]
)
