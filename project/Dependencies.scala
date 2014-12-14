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
import sbt._
import Keys._

object Dependencies {
  val resolutionRepos = Seq()

  object Urls {
    val javaClient = "https://github.com/snowplow/java-serf-client/archive/%s.zip"
  }

  object V {
    val javaClient = "ce8a045b0c40676b530798adea9396b0faadef42" // Compiled in BuildSettings
    val msgpack    = "0.6.11"
    val guava      = "17.0"
    val slf4jApi   = "1.7.7"
  }

  object Libraries {
    val msgpack    = "org.msgpack"        % "msgpack"     % V.msgpack
    val guava      = "com.google.guava"   % "guava"       % V.guava
    val slf4jApi   = "org.slf4j"          % "slf4j-api"   % V.slf4jApi
  }
}
