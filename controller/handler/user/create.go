package user

import (
	"axia/model/user"
	service "axia/service/user"
	"axia/util"
	"fmt"
	"net/http"

	log "github.com/Sirupsen/logrus"

	"github.com/gin-gonic/gin"
)

/**
 * Author   : Dynastymasra
 * Name     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */

// CreateUserController used to controller from request to service
func CreateUserController(c *gin.Context) {
	var data user.Identity

	if c.BindJSON(&data) == nil {
		identity, err := user.IdentityToThriftModel(&data)
		if err != nil {
			log.Error(fmt.Sprintf("CreateUserController - ConvertError: %v", err))
			c.JSON(http.StatusPreconditionFailed, util.FailResponse(fmt.Sprintf("%v", err)))
			return
		}
		result, err := service.CreateService(identity)

		switch e := err.(type) {
		case error:
			log.Error(fmt.Sprintf("CreateUserController - Error: %v", e))
			c.JSON(http.StatusServiceUnavailable, util.FailResponse(fmt.Sprintf("%v", e)))
		default:
			c.JSON(http.StatusCreated, util.ObjectResponse(result))
		}
	} else {
		log.Error(fmt.Sprintf("CreateUserController - ErrorBadRequest: %v", c.BindJSON(&data)))
		c.JSON(http.StatusBadRequest, util.FailResponse("Not valid request parameters."))
	}
}
