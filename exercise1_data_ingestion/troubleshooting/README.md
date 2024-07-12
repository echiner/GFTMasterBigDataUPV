# Troubleshooting: Certificate Expired

In order to connect to Coinbase, we need to use secure connection (that is, secure websocket or WSS), so a certificate is required to perform the connection.
As you might know, certificates have an expiration date, so you might find that the Coinbase connectivity is not working fine, showing a certificate error. 

In order to fix this we need to do the following:

* Download the Coinbase certificate
  * Go to https://ws-feed.pro.coinbase.com/
  * Download the certificate ("Lock icon" --> Certificate --> Details --> "Copy to file..." and choose the first option "DER encoded binary")
  * Store it in the same folder as the current trustore (```<GIT_PROJECT>\docker-upv\config```)
* Add the certificate to the trustore. We will use "keytool" for that, which is included in any JDK:

```
keytool -keystore truststore.jks -alias coinbase -import -file coinbase.cer
```

(when asked for the keystore password, use the "Trustore Passwd" used in the exercise)

Example using Windows:

```
C:\"Program Files"\Java\jdk-20\bin\keytool --keystore .\truststore.jks -alias coinbase2023 -import -file .\pro.coinbase.der
```

(if the alias already exists, feel free to change it since it does not affect the execution itself)

* Restart NiFi

```
docker compose restart nifi
```

## Bonus: Start from scratch

If you want to completely remove the keystore and trustore, and start from scratch you can do so by following these steps:

* Download the NiFi Toolkit from https://nifi.apache.org/download.html
* Run the following command, which will generate the files and include the required certificates, etc. from NiFi (write down any passwords generated during the process):

```
.\tls-toolkit.bat standalone -n "localhost"
```

* Dowload the Coinbase certificate and add it to the trustore (as seen above)
* Use the newly created files in the NiFi config (keystore and trustore filename)
* Introduce the credentials ("keystore password", "key password" and "trustore password")