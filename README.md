# Go Client Microservice with Gin Framework and Apache Thrift

[![Go](https://img.shields.io/badge/Go-1.6-00E5E6.svg)](https://golang.org/)
[![Gin](https://img.shields.io/badge/Framework-Gin-blue.svg)](https://github.com/gin-gonic/gin)
[![Thrift](https://img.shields.io/badge/Apache%20Thrift-0.9.3-yellow.svg)](https://thrift.apache.org/)
[![License](https://img.shields.io/badge/license-MIT-44897A.svg)](https://github.com/dynastymasra/ScalaGolangThirft/blob/master/LICENSE)

Golang (Go Programming Language) client microservice with Apache Thrift and Gin Framework, Server use scala.

## Libraries

- Golang Logger: http://gopkg.in/natefinch/lumberjack.v2
- Golang Log: https://github.com/Sirupsen/logrus
- Golang Thrift: http://git.apache.org/thrift.git/lib/go/thrift
- Golang Environment: https://github.com/joho/godotenv

## Package structures

- `controller` : Used to manage all request.
- `engine` : Place where main go application.
- `model` : Used to model data from and to thrift service.
- `service` : Used to handle transport to thrift.
- `thrift` : Folder where generated thrift file places.
- `util` : Used to place util helper.

## How To Install

1. Make sure you have installed latest golang (`1.6.x`) in your os.
2. Choose your working directory, example: `/document/go`
3. Set os environment `GOPATH` that point to your chosen working directory (`/document/go`)
4. Create three main directories inside `/document/go`: `bin` , `pkg`, `src`
5. Clone the repo use command: `git clone <repo_url> axia`

After following these five steps, then inside your src directories should be:

```
axia
├── controller
├── engine
├── model
├── service
├── thrift
├── util
├── LICENSE
└── README.md
```

Go to `/document/go/src/axia` and run :

```
go get
```

To download all necessary dependencies.


## How To Compile

Use command line inside working directory, and go to `axia` directory :

```
go get
go build --race
```

Always use `--race` parameter to detect race condition

## How To Run

Use command line inside working directory, and go to `axia` directory :

```
go run main.go -flag...
```

## How To Build

Use `Golang` command `GOOS` and `GOARCH` to build golang Application with specific `OS`

```
example GOOS=<goosList> GOARCH=<goarchList> go build <package or file>
```

`goosList` = `"android darwin dragonfly freebsd linux nacl netbsd openbsd plan9 solaris windows"`

`goarchList` = `"386 amd64 amd64p32 arm armbe arm64 arm64be ppc64 ppc64le mips mipsle mips64 mips64le mips64p32 mips64p32le`

## Important

Application have `flag` to run. before run this `app` used flag:

```
- mode     = Gin mode use release or debug.
- addr     = Address application will run.
- log      = Location of log file generated.
- env      = Location environment file for application.
```
