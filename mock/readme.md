SERVER_ADDRESS=https://fm-android-kotlin-api.localmachine|https://e2bd65e4.ngrok.io
一、hotel
1、yarn global add hotel
2、hotel start
3、Settings -> Network & Internet -> Proxy -> Automatically detect settings -> Script address -> http://localhost:2000/proxy.pac
4、`hotel add "json-server db.json" --name fm-android-kotlin-api --port 2002` --port 2002===json-server.json "port": "2002"
5、hotel stop & hotel start
6、http://hotel.localmachine/
7、http://fm-android-kotlin-api.localmachine | https://fm-android-kotlin-api.localmachine

二、ngrok
1、json-server db.json
2、ngrok http 2002
