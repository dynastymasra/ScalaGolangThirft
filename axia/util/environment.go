package util

import (
	"fmt"
	"os"

	log "github.com/Sirupsen/logrus"
	"github.com/joho/godotenv"
)

/**
 * Author   : Dynastymasra
 * Name     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */

// CreateEnvironment used to auto create environment file for used application
func CreateEnvironment(env string) {
	file, err := os.Create(".env")
	if err != nil {
		log.Fatalln(err)
	}

	// This is sensitive environment, small change can make error
	serviceUser := "USER_SERVICE_HOST=localhost\nUSER_SERVICE_PORT=7100\n"
	stringLog := "\nLOGGER_STATE=DEVELOPMENT\nLOGGER_NAME=access.log\nLOGGER_SIZE=10\nLOGGER_BACKUPS=5\nLOGGER_AGE=30\n"
	var write = []byte(fmt.Sprintf("%v\n%v", serviceUser, stringLog))

	_, err = file.Write(write)
	if err != nil {
		log.Fatalln(err)
	}

	err = godotenv.Load(env)
	if err != nil {
		log.Fatalln(err)
	}
}
