
rem C:\OpenSSL-Win64\bin\openssl s_client -servername 192.168.1.100 -connect 192.168.1.100:443 > cert.pem

rem "C:\Program Files\Java\jdk1.8.0_131\bin\keytool" -import -keystore clientkeystore -file client.cer -alias 192.168.1.100

rem "C:\Program Files\Java\jdk1.8.0_131\bin\keytool"  -import -trustcacerts -file client.cer -alias cert -keystore clientkeystore

"C:\Program Files\Java\jdk1.8.0_131\bin\keytool" -list -keystore clientkeystore