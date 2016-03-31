package middleware

import (
	"net/http"
	"strings"

	"github.com/gin-gonic/gin"
)

/**
 * Author   : Dynastymasra
 * Name     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */

// RequestType used to check header request only accept json
func RequestType() gin.HandlerFunc {
	return func(c *gin.Context) {
		headers := c.Request.Header
		contentType := headers.Get("Content-Type")
		accept := headers.Get("Accept")

		if len(contentType) < 1 {
			c.AbortWithStatus(http.StatusUnsupportedMediaType)
		} else if len(accept) < 1 {
			c.AbortWithStatus(http.StatusUnsupportedMediaType)
		}

		if len(contentType) >= 1 && !strings.Contains(contentType, "application/json") {
			c.AbortWithStatus(http.StatusUnsupportedMediaType)
		}

		if len(accept) >= 1 && !strings.Contains(accept, "application/json") {
			c.AbortWithStatus(http.StatusUnsupportedMediaType)
		}

		c.Next()
	}
}
