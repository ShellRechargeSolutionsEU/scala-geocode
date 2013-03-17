package com.thenewmotion.geocode

import net.liftweb.json._
import dispatch._

/**
 * @author Yaroslav Klymko
 */
class Geocode(http: Http = Http) {

  import Geocode._

  private val req = url(googleapis) / "maps" / "api" / "geocode" / "json"
  implicit val formats = jsonFormats

  /**
   * This call to google service is limited
   * @see https://developers.google.com/maps/documentation/geocoding/#Limits
   */
  def ?(location: Location): Either[Error, List[ResponseResult]] = {
    import location._
    val latlng = ("latlng", "%s,%s".format(latitude, longitude))
    val json  = parse(http(req <<? List(latlng, ("sensor" -> "false")) OK (as.String))())
    val response = Extraction.extract[GeocodeResponse](json)
    response.status match {
      case ResponseStatus.ZeroResults    ⇒ Left(ZeroResults)
      case ResponseStatus.OverQueryLimit ⇒ Left(OverQuotaLimit)
      case ResponseStatus.RequestDenied  ⇒ Left(Denied)
      case ResponseStatus.InvalidRequest ⇒ Left(InvalidRequest)
      case _                             ⇒ Right(response.results)
    }
  }
}

object Geocode {
  val googleapis = "http://maps.googleapis.com"
  val geocodeJson = "maps/api/geocode/json"

  val responseStatusSerializer = new Serializer[ResponseStatus.Value] {
    private val StatusClass = classOf[ResponseStatus.Value]

    def deserialize(implicit format: Formats) = {
      case (TypeInfo(StatusClass, _), JString(s)) => ResponseStatus(s)
    }

    def serialize(implicit format: Formats) = {
      case status: ResponseStatus.Value => JString(status.toString)
    }
  }

  val jsonFormats = (DefaultFormats + responseStatusSerializer)
}
