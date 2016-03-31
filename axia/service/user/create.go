package user

import (
	"axia/thrift/user"
	"net"
	"os"

	"git.apache.org/thrift.git/lib/go/thrift"
	log "github.com/Sirupsen/logrus"
)

/**
 * Author   : Dynastymasra
 * Name     : Dimas Ragil T
 * Email    : dynastymasra@gmail.com
 * LinkedIn : http://www.linkedin.com/in/dynastymasra
 * Blogspot : dynastymasra.wordpress.com | dynastymasra.blogspot.com
 */

// CreateService func used to get service user ping response
func CreateService(data *user.Identity) (*user.Identity, error) {
	host := os.Getenv("USER_SERVICE_HOST")
	port := os.Getenv("USER_SERVICE_PORT")

	protocolFactory := thrift.NewTBinaryProtocolFactoryDefault()
	transportFactory := thrift.NewTFramedTransportFactory(thrift.NewTTransportFactory())
	socket, err := thrift.NewTSocket(net.JoinHostPort(host, port))
	if err != nil {
		log.Error(err)
		return nil, err
	}

	transport := transportFactory.GetTransport(socket)
	client := user.NewUserServiceClientFactory(transport, protocolFactory)
	defer client.Transport.Close()

	if err = client.Transport.Open(); err != nil {
		log.Error(err)
		return nil, err
	}

	result, err := client.Create(data)
	if err != nil {
		log.Error(err)
		return nil, err
	}

	return result, nil
}
