
# Scala Serf Client

A minimal Scala wrapper around the excellent **[Java Serf Client] [java-serf-client]**.

## Installation

Add this to your SBT config:

```scala
// Resolvers
val snowplowRepo = "SnowPlow Repo" at "http://maven.snplow.com/releases/"

// Dependency
val scalaSerfClient = "com.snowplowanalytics"  %% "scala-serf-client"  % "0.1.0"
```

Note the double percent (%%) between the group and artifactId. That'll ensure you get the right package for your Scala version.

## Quickstart

Terminal 1:

```bash
$ wget -q https://dl.bintray.com/mitchellh/serf/0.6.0_linux_amd64.zip
$ unzip 0.6.0_linux_amd64.zip
$ sudo mv serf /usr/local/bin/
$ sudo /usr/local/bin/serf agent -rpc-addr=0.0.0.0:4000
```

Terminal 2:

```scala
$ git clone https://github.com/snowplow/scala-serf-client.git
$ cd scala-serf-client
$ sbt console
scala> import no.tv2.serf.client.{SocketEndpoint, Client, UserEvent}
import no.tv2.serf.client.{SocketEndpoint, Client, UserEvent}

scala> val ep = new SocketEndpoint("127.0.0.1", 4000)
ep: no.tv2.serf.client.SocketEndpoint = no.tv2.serf.client.SocketEndpoint@1c2b43af

scala> val client = new Client(ep)
client: no.tv2.serf.client.Client = no.tv2.serf.client.Client@15f4bf3d

scala> client.handshake()
res0: no.tv2.serf.client.EmptyResponse = no.tv2.serf.client.EmptyResponse@4d627e35

scala> val subscription = client.stream("user:restart")
subscription: no.tv2.serf.client.StreamSubscription = no.tv2.serf.client.StreamSubscription@9cbf296

scala> System.out.println(subscription.take().asInstanceOf[UserEvent].getPayload) // Blocking...
```

Terminal 3:

```
$ serf event -rpc-addr=0.0.0.0:4000 restart NOW!
```

Check back in Terminal 2:

```
scala> System.out.println(subscription.take().asInstanceOf[UserEvent].getPayload) // Blocking...
NOW!
```

## Copyright and license

Scala Serf Client is copyright 2014 Snowplow Analytics Ltd.

Licensed under the **[Apache License, Version 2.0] [license]** (the "License");
you may not use this software except in compliance with the License.

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

[java-serf-client]: https://github.com/tv2norge/java-serf-client
[license]: http://www.apache.org/licenses/LICENSE-2.0
