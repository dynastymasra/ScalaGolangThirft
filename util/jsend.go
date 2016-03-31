package util

/**
 * Author   : Dynastymasra
 * Name     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */

//Jsend used to declare json key response status
type Jsend struct {
	Status string `json:"status" binding:"required"`
}

// JsendMessage used to declare json key response for failed return
type JsendMessage struct {
	Jsend
	Message string `json:"message" binding:"required"`
}

// JsendObject used to declare response json key for return with object or list
type JsendObject struct {
	Jsend
	Object interface{} `json:"data" binding:"required"`
}

// FailResponse used to create response fail message
func FailResponse(msg string) JsendMessage {
	response := JsendMessage{}
	response.Status = "failed"
	response.Message = msg

	return response
}

// SuccessResponse used to create response success with message
func SuccessResponse(msg string) JsendMessage {
	response := JsendMessage{}
	response.Status = "success"
	response.Message = msg

	return response
}

// SuccessEmptyResponse used to create response succes without body
func SuccessEmptyResponse() Jsend {
	response := Jsend{}
	response.Status = "sucsess"

	return response
}

// ObjectResponse used to return response data with object
func ObjectResponse(data interface{}) JsendObject {
	response := JsendObject{}
	response.Status = "success"
	response.Object = data

	return response
}
