package com.thenewmotion.geocode

import org.specs2.mutable.Spec
import scala.concurrent._, duration._, ExecutionContext.Implicits.global


class LocalityGeocodeSpec extends Spec {
  "LocalityGeocode" should {
    "be denied when key is wrong" in {
      val wrongGoogleClientKey = GoogleClientKey("")
      val geocode = new LocalityGeocode(googleClientKey = wrongGoogleClientKey)

      def ?(x: Location) = Await.result(geocode ? x, 3.seconds)

      ?(Location(50.516196, 30.466651)) must beLeft
      ?(Location(50.516196, 30.466651)).left.get must be(Denied)
    }
  }
}
