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
server.use(jsonServer.bodyParser)
server.use(middlewares);
server.use(function (req, res, next) {
    console.log("body::")
    console.log(req.body)
    if (req.method !== "GET") {
        req.method = "GET"
        for (var key in req.body) {
            if (key === "keyword") {
                req.query["goodsDesc_like"] = req.body[key]
                delete req.body[key]
            } else if (key === "goodsId") {
                req.query["id"] = req.body[key] + ""
                delete req.body[key]
            } else {
                req.query[key] = typeof req.body[key] === "number" ? req.body[key] + "" : req.body[key]
            }
        }
        console.log("query::")
       console.log(req.query)
        delete req.body
    }
    next()
})
router.render = (req, res) => {
  let { data } =  res.locals
  if (req.originalUrl === "/goods/getGoodsDetail" && Array.isArray(data)) {
    data = data[0]
  }
  res.jsonp({
    status: 0,
    message: "接口调用成功",
    data: data
  })
}
server.use(jsonServer.rewriter(routes))
server.use(router);

https.createServer(options, server).listen(3002, function() {
  console.log("json-server started on port " + 3002);
});