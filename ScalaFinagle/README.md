# Scala Server Microservice with Finagle and Scrooge use Cassandra

[![Scala](https://img.shields.io/badge/Scala-2.11.8-red.svg)](https://golang.org/)
[![Finagle](https://img.shields.io/badge/Finagle-6.34.0-6B809C.svg)](https://twitter.github.io/finagle/)
[![Thrift](https://img.shields.io/badge/Apache%20Thrift-0.9.3-yellow.svg)](https://thrift.apache.org/)
[![License](https://img.shields.io/badge/license-MIT-44897A.svg)](https://github.com/dynastymasra/ScalaGolangThirft/blob/master/LICENSE)

Scala server microservice with Apache Thrift, Finagle and Scrooge, Client use Golang(Go). `Cassandra` used to save data

## Libraries

- Scrooge          : 4.6.0
- Typesafe Config  : 1.3.0
- Typesafe Logging : 3.1.0
- Logback          : 1.1.6
- Phantom          : 1.22.0
- Junit            : 4.12
- Scalatest        : 2.2.4
- Websudos Util    : 0.9.11

## Package structures

- `controller` : Used to manage all request.
- `domain` : Used to convert data to model.
- `entity` : Used to declare model database cassandra.
- `model` : Used to handle entity cassandra.
- `repository` : Used to connect database.
- `util` : Used to place util helper.
- `value` : Used to declare value.

## How To Install

Use `SBT` to run and test this application


Go to `/ScalaFinagle` and run :

```
sbt run
```


## How To Compile

Use command line inside working directory, and go to `ScalaFinagle` directory :

```
sbt clean compile
```


## How To Run

Use command line inside working directory, and go to `ScalaFinagle` directory :

```
sbt run -flag....
```

## Important

Application have `flag` to run. before run this `app` used flag:

```
- Dapp.host                = Host application thrift running.
- Dapp.port                = Port application thrift running.
- Dcassandra.host.0        = Cassandra host with sequence.
- Dcassandra.port          = Cassandra port.
- Dcassandra.username      = Cassandra Username.
- Dcassandra.password      = Cassandra Password.
- Dcassandra.keyspace      = Cassandra keyspace.
```
