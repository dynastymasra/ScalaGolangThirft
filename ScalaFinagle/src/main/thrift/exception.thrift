namespace java com.twitter.finagle.axia.exception
namespace go axia.thrift.exception
#@namespace scala com.twitter.finagle.axia.exception

exception CannotConvertStringException {
    1: string description
}

exception UserAlreadyRegisterException {
    1: string description
}

exception UserNotFoundException {
    1: string description
}

exception ForbiddenException {
    1: string description
}

exception ValidationException {
    1: string description
}

exception CommonException {
    1: string description
}