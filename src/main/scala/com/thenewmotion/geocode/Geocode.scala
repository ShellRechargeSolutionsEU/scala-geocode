package com.thenewmotion.geocode

import scala.concurrent.ExecutionContext
import dispatch._
import Formats._


class Geocode(http: Http = Http) {

  /**
   * This call to google service is limited
   * @see https://developers.google.com/maps/documentation/geocoding/#Limits
   */
  def ?(l: Location)(implicit ec: ExecutionContext): Future[Either[Error, List[Result]]] = {
    import l._
    val parameters = List(
      "latlng" -> s"$latitude,$longitude",
      "sensor" -> "false"
    )
    val req = url("https://maps.googleapis.com/maps/api/geocode/json") <<? parameters
    http(req OK as.String).map(parseResult)
  }
}


