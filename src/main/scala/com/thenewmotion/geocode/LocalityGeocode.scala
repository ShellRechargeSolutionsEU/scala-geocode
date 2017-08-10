package com.thenewmotion.geocode


import dispatch._


case class GoogleClientKey(key: String) extends AnyVal

class LocalityGeocode(protected val http: Http = Http.default, googleClientKey: GoogleClientKey) extends GeocodeBase {

  protected def requestParameters(loc: Location) = {
     import loc._

    List(
      "latlng" -> s"$latitude,$longitude",
      "sensor" -> "false",
      "result_type"  -> "locality",
      "key" -> s"${googleClientKey.key}"
    )
  }
}