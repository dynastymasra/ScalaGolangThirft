package routes

import (
	"axia/controller/handler/user"

	"github.com/gin-gonic/gin"
)

/**
 * Author   : Dynastymasra
 * Name     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */

// UserRouters used to set router user service
func UserRouters(router *gin.RouterGroup) {
	router.POST("/user", user.CreateUserController)

	router.GET("/user/ping", user.PingUserController)
	router.GET("/user/detail/:id", user.GetUserController)
	router.GET("/user/all", user.GetAllUserController)

	router.PUT("/user/:id", user.UpdateUserController)

	router.DELETE("/user/:id", user.DeleteUserController)
}
