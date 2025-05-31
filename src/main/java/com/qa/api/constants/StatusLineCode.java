package com.qa.api.constants;

public enum StatusLineCode {
	
	OK,
	Created,
	Accepted,
	No_Content,
	Partial_Content,
	
	Bad_Request,
	Unauthorized,
	Forbidden,
	Not_Found, 
	Method_Not_Allowed,
	Conflict,
	
	Internal_Server_Error,
	Bad_Gateway,
	Service_Unavailable
	
}
	
/*	List of common HTTP status codes used in REST APIs, categorized as below: 
	1xx Informational 

	• 100 Continue: The server has received the initial part of a request and is awaiting further information. 
	• 101 Switching Protocols: The server is switching protocols.  

	2xx Successful  

	• 200 OK: The request was successful. 
	• 201 Created: The request was successful, and a new resource was created. 
	• 202 Accepted: The request was accepted for processing, but the processing is not yet complete. 
	• 204 No Content: The request was successful, but there's no content to return. 
	• 206 Partial Content: The request was successful, and only part of the requested resource is returned. 

	3xx Redirection

	• 301 Moved Permanently: The resource has permanently moved to a different URL. 
	• 302 Found: The resource has temporarily moved to a different URL. 
	• 303 See Other: The server is redirecting to another URL, and the new URL is in the Location header. 
	• 304 Not Modified: The resource has not been modified since the last request.  

	4xx Client Error 

	• 400 Bad Request: The server cannot process the request due to a client error. 
	• 401 Unauthorized: The request requires authentication.   
	• 403 Forbidden: The server refuses to authorize the request. 
	• 404 Not Found: The requested resource cannot be found.  
	• 405 Method Not Allowed: The requested HTTP method is not allowed for the resource.  
	• 409 Conflict: The request could not be completed due to a conflict with the current state of the resource.   

	5xx Server Error 

	• 500 Internal Server Error: The server encountered an unexpected error. 
	• 502 Bad Gateway: The server acting as a gateway or proxy received an invalid response. 
	• 503 Service Unavailable: The server is temporarily unavailable.   
*/

