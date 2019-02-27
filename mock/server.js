var fs = require('fs'),
  https = require('https'),
  jsonServer = require('json-server'),
  server = jsonServer.create(),
  router = jsonServer.router('db.json'),
  middlewares = jsonServer.defaults();

var options = {
  key: fs.readFileSync('./key.pem'),
  cert: fs.readFileSync('./cert.pem')
};

server.use(middlewares);
server.use(router);

https.createServer(options, server).listen(3002, function() {
  console.log("json-server started on port " + 3002);
});