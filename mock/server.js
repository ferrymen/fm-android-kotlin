var fs = require('fs'),
  https = require('https'),
  jsonServer = require('json-server'),
  server = jsonServer.create(),
  router = jsonServer.router('db.json'),
  middlewares = jsonServer.defaults(),
  routes = require('./routes.json');

// https://stackoverflow.com/questions/26663404/webpack-dev-server-running-on-https-web-sockets-secure
var options = {
  key: fs.readFileSync('./ssl/localhost+1-key.pem'),
  cert: fs.readFileSync('./ssl/localhost+1.pem')
};

// Add this before server.use(router)
server.use(middlewares);
server.use(function (req, res, next) {
    req.method = "GET"
    next()
})
server.use(jsonServer.rewriter(routes))
server.use(router);

https.createServer(options, server).listen(3002, function() {
  console.log("json-server started on port " + 3002);
});