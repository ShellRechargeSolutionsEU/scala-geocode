package com.thenewmotion.geocode

import net.liftweb.json._
import dispatch._

/**
 * @author Yaroslav Klymko
 */
class Geocode() {

  import Geocode._

  private val req = url(googleapis) / geocodeJson
  implicit val formats = jsonFormats

  /**
   * This call to google service is limited
   * @see https://developers.google.com/maps/documentation/geocoding/#Limits
   */
  def ?(location: Location): GeocodeResponse = {
    import location._
    val latlng = ("latlng", "%s,%s".format(latitude, longitude))
    val json = Http(req <<? List(latlng, ("sensor", "false")) >- parse)
    Extraction.extract[GeocodeResponse](json)
  }
}

object Geocode {
  val googleapis = "http://maps.googleapis.com"
  val geocodeJson = "maps/api/geocode/json"

  val responseStatusSerializer = new Serializer[ResponseStatus.Value] {
    private val StatusClass = classOf[ResponseStatus.Value]

    def deserialize(implicit format: Formats) = {
      case (TypeInfo(StatusClass, _), JString(s)) => ResponseStatus.apply(s)
    }

    def serialize(implicit format: Formats) = {
      case status: ResponseStatus.Value => JString(status.toString)
    }
  }

  val jsonFormats = (DefaultFormats + responseStatusSerializer)
}