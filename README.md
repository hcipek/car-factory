# Project Car Factory

This project is returns a String to Client, depends on parameter sended.
Available Parameter Values : Cabrio, Sedan, Hatchback.

Any other value will return INVALID_VALUE message.

# How to use;

Import Project with your IDE,

Run the application.

Use Postman with given URL Requests.

# Available Requests

 http://localhost:8080/api/
  Requires : None
  Returns  : A warm welcome.
 
 http://localhost:8080/api/getsample
  Requires : String parameter that one of Cabrio, Sedan, Hatchback
  Returns  : 'name' Car has produced.
  Throws   : INVALID_REQUEST, INVALID_VALUE, UNEXPECTED_ERROR
 
 http://localhost:8080/api/getsamplewithbody
  Requires : Request Body with field 'name' that one of Cabrio, Sedan, Hatchback
  Returns  : Response Body that contains result property with value 'name' Car has produced.
  Throws   : Response Body that contains result property with value INVALID_VALUE, UNEXPECTED_ERROR

Any other request in http://localhost:8080/ without given mappings return UNSUPPORTED OPERATION message.

Examples available on Postman Documentary.

Postman Documentary : https://documenter.getpostman.com/view/5713788/TVsrEUJy
