include "exception.thrift"

namespace java com.twitter.finagle.axia.user
namespace go axia.thrift.user
#@namespace scala com.twitter.finagle.axia.user

typedef string timestamp
typedef string UUID

enum Gender {
    MALE,
    FEMALE
}

struct Identity {
    1: optional UUID id
    2: required string identityId,
    3: required string firstname,
    4: optional string middlename,
    5: optional string lastname,
    6: required string birthplace,
    7: required timestamp birthdate,
    8: required Gender gender,
    9: required string nationality,
    10: required UUID villageId,
    11: required string address,
    12: required string phone,
    13: optional string email,
    14: required UUID religion,
    15: required string profession,
    16: required bool active,
    17: required timestamp created,
    18: required timestamp updated
}

service UserService {
    string ping()

    Identity create(1: required Identity identity) throws (1: exception.UserAlreadyRegisterException alreadyRegister,
        2: exception.ForbiddenException forbidden, 3: exception.ValidationException validation, 4: exception.CommonException common)

    Identity update(1: required UUID id, 2: required Identity identity) throws (1: exception.UserNotFoundException userNotFound,
        2: exception.ForbiddenException forbidden, 3: exception.ValidationException validation, 4: exception.CommonException common)

    void remove(1: required UUID id) throws (1: exception.UserNotFoundException userNotFound,
        2: exception.ForbiddenException forbidden, 3: exception.CommonException common)

    Identity get(1: required UUID id) throws (1: exception.UserNotFoundException userNotFound,
        2: exception.ForbiddenException forbidden, 3: exception.CommonException common)

    list<Identity> getAll() throws (1: exception.ForbiddenException forbidden, 2: exception.CommonException common)
}