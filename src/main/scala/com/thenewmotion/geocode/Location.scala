package com.thenewmotion.geocode

/**
 * @author Yaroslav Klymko
 */
case class Location(latitude: Double, longitude: Double)

object Location {
  def apply(latitude: String, longitude: String): Location =
    Location(latitude.toDouble, longitude.toDouble)
}