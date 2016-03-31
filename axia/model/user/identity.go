package user

import (
	"axia/thrift/user"
	"strings"
)

/**
 * Author   : Dynastymasra
 * Name     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */

// Identity struct used to mapping request json
type Identity struct {
	ID          *user.UUID `json:"id,omitempty"`
	IdentityID  string     `json:"identityId" binding:"required"`
	Firstname   string     `json:"firstname" binding:"required"`
	Middlename  *string    `json:"middlename,omitempty"`
	Lastname    *string    `json:"lastname,omitempty"`
	Birthplace  string     `json:"birthplace" binding:"required"`
	Birthdate   string     `json:"birthdate" binding:"required"`
	Gender      string     `json:"gender" binding:"required"`
	Nationality string     `json:"nationality" binding:"required"`
	VillageID   string     `json:"villageId" binding:"required"`
	Address     string     `json:"address" binding:"required"`
	Phone       string     `json:"phone" binding:"required"`
	Email       *string    `json:"email,omitempty"`
	Religion    string     `json:"religion" binding:"required"`
	Profession  string     `json:"profession" binding:"required"`
	Active      bool       `json:"active" binding:"required"`
	Created     string     `json:"created" binding:"required"`
	Updated     string     `json:"updated" binding:"required"`
}

// IdentityToThriftModel used to convert model identity to thrift identity model
func IdentityToThriftModel(i *Identity) (*user.Identity, error) {

	ti := user.NewIdentity()
	if i.ID != nil {
		ti.ID = i.ID
	}

	ti.IdentityId = i.IdentityID
	ti.Firstname = i.Firstname

	if i.Middlename != nil {
		ti.Middlename = i.Middlename
	}

	if i.Lastname != nil {
		ti.Lastname = i.Lastname
	}

	ti.Birthplace = i.Birthplace
	ti.Birthdate = user.Timestamp(i.Birthdate)

	gender, err := user.GenderFromString(strings.ToUpper(i.Gender))
	if err != nil {
		return nil, err
	}
	ti.Gender = gender

	ti.Nationality = i.Nationality
	ti.VillageId = user.UUID(i.VillageID)
	ti.Address = i.Address
	ti.Phone = i.Phone

	if i.Email != nil {
		ti.Email = i.Email
	}

	ti.Religion = user.UUID(i.Religion)
	ti.Profession = i.Profession
	ti.Active = i.Active
	ti.Created = user.Timestamp(i.Created)
	ti.Updated = user.Timestamp(i.Updated)

	return ti, nil
}
