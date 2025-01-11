const http = require("http");
const os = require('os')

console.log("app server is starting...");

var handler = function(request, response){
console.log("Requested from :" + request.connection.remoteAddress);
		response.writeHead(200);
	response.end("Hi from" + os.hostname() + "\n");
}
var www = http.createServer(handler);
www.listen(8080);
