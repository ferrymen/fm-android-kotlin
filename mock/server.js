var fs = require('fs'),
  https = require('https'),
  jsonServer = require('json-server'),
  server = jsonServer.create(),
  router = jsonServer.router('db.json'),
  middlewares = jsonServer.defaults(),
  routes = require('./routes.json');

var low = require('lowdb')
var FileSync = require('lowdb/adapters/FileSync')

var adapter = new FileSync('db.json')
var db = low(adapter)

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
    console.log(req.path)
    if (req.method !== "GET") {
        req.method = "GET"
        if (req.path === "/cart/add") {
            var cartListSize = db.get('cartList').size().value()
            db.get('cartList')
              .push(Object.assign({}, req.body, {id: cartListSize++}))
              .write()

            db.get('cartAdd')
              .update('count', function () {
                  return db
                    .get('cartList')
                    .size()
                    .value()
                  })
              .write()
            } else if (req.path === "/cart/delete") {
                db.get('cartList')
                  .remove(function (item) {
                    return req.body.cartIdList.indexOf(item.id) > -1
                  })
                  .write()

                  db.get('cartAdd')
                    .update('count', function () {
                        return db
                          .get('cartList')
                          .size()
                          .value()
                        })
                    .write()
            } else {
            for (var key in req.body) {
                if (key === "keyword") {
                    req.query["goodsDesc_like"] = req.body[key]
                    delete req.body[key]
                } else if (key === "goodsId") {
                    req.query["id"] = req.body[key] + ""
                    req.query["_embed"] = "goodsSku"
                    delete req.body[key]
                } else {
                    req.query[key] = typeof req.body[key] === "number" ? req.body[key] + "" : req.body[key]
                }
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
    data["goodsSku"] = db.get('goodsSku')
      .filter({ goodsId: +req.query.id })
      .value()
//    data["goodsSku"] = res.locals.data["goodsSku"].find(item => item.goodsId === req.query.id)
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