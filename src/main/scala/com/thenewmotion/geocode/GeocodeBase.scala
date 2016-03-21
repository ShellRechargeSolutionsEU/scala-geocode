package com.thenewmotion.geocode

import scala.concurrent.ExecutionContext
import dispatch._
import Formats._


trait GeocodeBase {

  protected def http: Http

  protected def requestParameters(loc: Location): List[(String, String)]

  /**
   * This call to google service is limited
   * @see https://developers.google.com/maps/documentation/geocoding/#Limits
   */
  def ?(l: Location)(implicit ec: ExecutionContext): Future[Either[Error, List[Result]]] = {
    val req = url("https://maps.googleapis.com/maps/api/geocode/json") <<? requestParameters(l)
    http(req OK as.String).map(parseResult)
  }
}
