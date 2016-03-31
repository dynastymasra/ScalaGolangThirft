package user

import (
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

// GetAllUserController used to controller from request to service
func GetAllUserController(c *gin.Context) {
	result, err := service.GetAllUserService()

	switch e := err.(type) {
	case error:
		log.Error(fmt.Sprintf("GetAllUserController - Error: %v", e))
		c.JSON(http.StatusServiceUnavailable, util.FailResponse(fmt.Sprintf("%v", e)))
	default:
		c.JSON(http.StatusOK, util.ObjectResponse(result))
	}
}
