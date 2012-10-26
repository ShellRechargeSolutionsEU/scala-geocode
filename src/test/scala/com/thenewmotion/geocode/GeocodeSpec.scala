package com.thenewmotion.geocode

import org.specs2.mutable.SpecificationWithJUnit

/**
 * @author Yaroslav Klymko
 */
class GeocodeSpec extends SpecificationWithJUnit {
  "Geocode" should {
    "find data by location" in {
      val geocode = new Geocode()
      (geocode ? Location(50.516196, 30.466651)).status mustEqual ResponseStatus.Ok
      (geocode ? Location(50.445057, 30.521049)).status mustEqual ResponseStatus.Ok
      (geocode ? Location(51.498685, -0.12967)).status mustEqual ResponseStatus.Ok
    }
  }
}