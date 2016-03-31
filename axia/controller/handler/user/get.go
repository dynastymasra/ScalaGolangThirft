package user

import (
	service "axia/service/user"
	"axia/thrift/user"
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

// GetUserController used to controller from request to service
func GetUserController(c *gin.Context) {
	id := c.Param("id")

	result, err := service.GetUserService(user.UUID(id))

	switch e := err.(type) {
	case error:
		log.Error(fmt.Sprintf("GetUserController - Error: %v", e))
		c.JSON(http.StatusServiceUnavailable, util.FailResponse(fmt.Sprintf("%v", e)))
	default:
		c.JSON(http.StatusOK, util.ObjectResponse(result))
	}
}
