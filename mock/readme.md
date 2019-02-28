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

三、mkcert(recommend)
https://stackoverflow.com/questions/26663404/webpack-dev-server-running-on-https-web-sockets-secure
https://chocolatey.org/
1、mkcert -install
2、mkcert localhost 10.0.2.2
3、https.createServer(
    key: fs.readFileSync('./ssl/localhost+1-key.pem'),
    cert: fs.readFileSync('./ssl/localhost+1.pem')
)
4、nodemon --watch routes.json --watch db.json server.js
5、
1)、`rootCA.pem` file in the folder printed by `mkcert -CAROOT`(C:/Users/User/$User/AppData/Local/mkcert/rootCA.pem)
2）、Android Studio: View -> Tool Windows -> Device File Explorer -> Upload(C:/Users/User/$User/AppData/Local/mkcert/rootCA.pem)
3）、
Add a file res/xml/network_security_config.xml to your app:

<network-security-config>
    <debug-overrides>
        <trust-anchors>
            <!-- Trust user added CAs while debuggable only -->
            <certificates src="user" />
        </trust-anchors>
    </debug-overrides>
</network-security-config>
Then add a reference to this file in your app's manifest, as follows:

<?xml version="1.0" encoding="utf-8"?>
<manifest>
    <application android:networkSecurityConfig="@xml/network_security_config">
    </application>
</manifest>
4）、Run App
5）、AVD：Settings -> Security -> Install from SD Card(CrootCA.pem)
https://github.com/FiloSottile/mkcert/commit/02f776146c3ac73b026c2640d4e71c72ce8e449d
https://stackoverflow.com/questions/4461360/how-to-install-trusted-ca-certificate-on-android-device/22040887#22040887
