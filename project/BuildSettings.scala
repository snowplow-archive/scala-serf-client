/*
 * Copyright (c) 2014 Snowplow Analytics Ltd. All rights reserved.
 *
 * This program is licensed to you under the Apache License Version 2.0,
 * and you may not use this file except in compliance with the Apache License Version 2.0.
 * You may obtain a copy of the Apache License Version 2.0 at http://www.apache.org/licenses/LICENSE-2.0.
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the Apache License Version 2.0 is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Apache License Version 2.0 for the specific language governing permissions and limitations there under.
 */

// SBT
import sbt._
import Keys._

object BuildSettings {

  // Basic settings for our app
  lazy val basicSettings = Seq[Setting[_]](
    organization  := "com.snowplowanalytics",
    version       := "0.1.0",
    description   := "Minimal Scala wrapper for Java Serf Client",
    scalaVersion  := "2.10.1",
    crossScalaVersions := Seq("2.9.2", "2.9.3", "2.10.1", "2.11.4"),
    scalacOptions := Seq("-deprecation", "-encoding", "utf8"),
    resolvers     ++= Dependencies.resolutionRepos
  )

  // For MaxMind support
  import Dependencies._
  lazy val javaClientSettings = Seq(

    // Download and compile the MaxMind GeoIP Java API from source
    // Adapted from https://github.com/guardian/maxmind-geoip-build/blob/master/project/Build.scala
    sourceGenerators in Compile <+= (sourceManaged in Compile) map { out =>
      val zip = new URL(Urls.javaClient format (V.javaClient))
      IO.unzipURL(zip, out)
      (out / "java-serf-client-%s".format(V.javaClient) / "src" / "main" / "java" / "no" / "tv2" / "serf" / "client" ** ("*.java")).get
    }
  )

  // Publish settings
  // TODO: update with ivy credentials etc when we start using Nexus
  lazy val publishSettings = Seq[Setting[_]](

    publishTo <<= version { version =>
      val basePath = "target/repo/%s".format {
        if (version.trim.endsWith("SNAPSHOT")) "snapshots/" else "releases/"
      }
      Some(Resolver.file("Local Maven repository", file(basePath)) transactional())
    }
  )

  lazy val buildSettings = basicSettings ++ javaClientSettings ++ publishSettings
}
