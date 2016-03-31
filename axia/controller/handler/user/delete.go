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

// DeleteUserController used to controller from request to service
func DeleteUserController(c *gin.Context) {
	id := c.Param("id")

	err := service.DeleteUserService(user.UUID(id))

	switch e := err.(type) {
	case error:
		log.Error(fmt.Sprintf("DeleteUserController - Error: %v", e))
		c.JSON(http.StatusServiceUnavailable, util.FailResponse(fmt.Sprintf("%v", e)))
	default:
		c.JSON(http.StatusOK, util.SuccessEmptyResponse())
	}
}
