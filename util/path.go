package util

import "os"

/**
 * Author   : Dynastymasra
 * Name     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */

// RootPath used to define base path application project
func RootPath() string {
	basePath := os.Getenv("GOPATH") + "/src/axia/"

	return basePath
}
