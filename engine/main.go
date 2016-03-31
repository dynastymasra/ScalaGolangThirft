package main

import (
	"axia/controller/middleware"
	"axia/controller/routes"
	"axia/util"
	"flag"

	log "github.com/Sirupsen/logrus"

	"github.com/gin-gonic/gin"
	"github.com/joho/godotenv"
)

/**
 * Author   : Dynastymasra
 * Name     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */

var (
	address = flag.String("addr", ":8000", "Address to run application")
	mode    = flag.String("mode", "release", "Mode to run http server api")
	logPath = flag.String("log", "", "Log file to redirect all log message")
	env     = flag.String("env", ".env", "Env file path")
)

func init() {
	flag.Parse()
	gin.SetMode(*mode)

	err := godotenv.Load(*env)
	if err != nil {
		log.Error(err)
		util.CreateEnvironment(*env)
	}

	util.InitLogger(*logPath)

	log.Info("Initialize engine...")
}

func main() {
	router := gin.Default()
	router.Use(middleware.RequestType())

	v1 := router.Group("/v1")
	{
		routes.UserRouters(v1)
	}

	log.Info("Start api server...")
	router.Run(*address)
}
