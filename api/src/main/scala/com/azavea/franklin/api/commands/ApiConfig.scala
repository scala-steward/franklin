package com.azavea.franklin.api.commands

case class ApiConfig(publicPort: Int, internalPort: Int, host: String, scheme: String) {

  val apiHost: String = (publicPort, scheme) match {
    case (443, "https") => s"$scheme://$host"
    case (80, "http")   => s"$scheme://$host"
    case _              => s"$scheme://$host:$publicPort"
  }

}
