package util

import (
	"fmt"
	"os"
	"strconv"

	log "github.com/Sirupsen/logrus"
	lumberjack "gopkg.in/natefinch/lumberjack.v2"
)

/**
 * Author   : Dynastymasra
 * Name     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */

// LumberjackLogger used to init created file log
func LumberjackLogger(filepath string, maxSize, maxBackups, maxAge int) *lumberjack.Logger {
	return &lumberjack.Logger{
		Filename:   filepath,
		MaxSize:    maxSize,
		MaxBackups: maxBackups,
		MaxAge:     maxAge,
	}
}

// LoggerInitDev used to init logger in development
func LoggerInitDev(path string) {
	log.SetFormatter(&log.TextFormatter{FullTimestamp: true, ForceColors: true})
	log.SetOutput(os.Stdout)
	log.WithFields(log.Fields{
		"logger": "development",
	}).Info("Init logger development")
}

// LoggerInitProd used to init logger in production
func LoggerInitProd(path string) {
	logSize, err := strconv.Atoi(os.Getenv("LOGGER_SIZE"))
	if err != nil {
		log.Errorln(err)
	}

	logBackups, err := strconv.Atoi(os.Getenv("LOGGER_BACKUPS"))
	if err != nil {
		log.Errorln(err)
	}

	logAge, err := strconv.Atoi(os.Getenv("LOGGER_AGE"))
	if err != nil {
		log.Errorln(err)
	}

	log.SetFormatter(&log.JSONFormatter{})
	log.WithFields(log.Fields{
		"logger":  "production",
		"path":    path,
		"size":    logSize,
		"backups": logBackups,
		"age":     logAge,
	}).Info("Init logger production")

	filepath := fmt.Sprintf("%v/%v", path, os.Getenv("LOGGER_NAME"))
	out := LumberjackLogger(filepath, logSize, logBackups, logAge)
	log.SetOutput(out)
}

// InitLogger used to init which setting logger to used
func InitLogger(path string) {
	logEnv := os.Getenv("LOGGER_STATE")
	switch logEnv {
	case "PRODUCTION":
		LoggerInitProd(path)
	case "DEVELOPMENT":
		LoggerInitDev(path)
	}
}
