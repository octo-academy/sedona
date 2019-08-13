package com.github.octoacademy.sedona.configuration

import java.net.InetAddress

import eu.timepit.refined.types.net.UserPortNumber

final case class Server(
  host: InetAddress,
  port: UserPortNumber
)
