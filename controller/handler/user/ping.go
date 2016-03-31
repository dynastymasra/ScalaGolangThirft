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

// PingUserController used to controller from request to service
func PingUserController(c *gin.Context) {
	result, err := service.PingService()

	switch e := err.(type) {
	case error:
		log.Error(e)
		c.JSON(http.StatusServiceUnavailable, util.FailResponse(fmt.Sprintf("%v", e)))
	default:
		c.JSON(http.StatusOK, util.SuccessResponse(result))
	}
}
