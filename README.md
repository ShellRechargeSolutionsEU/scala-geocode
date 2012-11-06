# Geocode client on Scala [![Build Status](https://secure.travis-ci.org/thenewmotion/scala-geocode.png)](http://travis-ci.org/thenewmotion/scala-geocode)

Google [geocode api](https://developers.google.com/maps/documentation/geocoding) client written on Scala


## Supports

* Getting geocode responses for a gps coordinates

## Example

```scala
      val geocode = new Geocode()
      geocode ? Location(51.498685, -0.12967) match {
            case Right(results)   =>
                results.foreach(r => println(r.formatted_address))
            case Left(error) => println(error)
      }

//      4B Deans Yd, Westminster, London, Greater London SW1P 3NP, UK
//      Westminster Abbey (Stop W), Westminster, City of Westminster, London SW1P, UK
//      Westminster, Westminster Abbey (S-bound), Westminster, City of Westminster, London SW1P, UK
//      Westminster, Westminster Abbey (Stop X), Westminster, City of Westminster, London SW1P, UK
//      Westminster Abbey (R), Westminster, City of Westminster, London SW1H, UK
//      Westminster, City of Westminster, London SW1P 3NY, UK
//      London, Greater London SW1P, UK
//      Westminster, London, UK
//      City of Westminster, Greater London, UK
//      London, UK
//      London, UK
//      Greater London, UK
//      England, UK
//      United Kingdom
```


## Setup

1. Add this repository to your pom.xml:
```xml
    <repository>
        <id>thenewmotion</id>
        <name>The New Motion Repository</name>
        <url>http://nexus.thenewmotion.com/content/repositories/releases-public</url>
    </repository>
```

2. Add dependency to your pom.xml:
```xml
    <dependency>
        <groupId>com.thenewmotion.geocode</groupId>
        <artifactId>geocode</artifactId>
        <version>1.0</version>
    </dependency>
```
