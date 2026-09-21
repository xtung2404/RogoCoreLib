# Rules nay tu dong di kem AAR sang moi app consume RogoCoreLib.

# --- Missing classes khi app bat R8 ---
# rgbaseapp.bR goi java.lang.System.Logger (API Java 9+), Android khong co.
-dontwarn java.lang.System$Logger
-dontwarn java.lang.System$Logger$Level
# Netty (transitive cua hivemq-mqtt-client) tham chieu BlockHound - chi dung khi test tren JVM.
-dontwarn reactor.blockhound.integration.BlockHoundIntegration

# --- Giu code Core ---
# 3 jar trong libs/ da obfuscate san va dung reflection/Gson noi bo,
# khong cho R8 cua app doi ten lan hai.
-keep class rogo.iot.module.** { *; }
-keep interface rogo.iot.module.** { *; }
-keep class rgbase.** { *; }
-keep class rgbaseapp.** { *; }
-keepattributes Signature, InnerClasses, EnclosingMethod, *Annotation*, Exceptions

# Luu y: KHONG them -dontwarn cho com.google.gson.** hay com.hivemq.client.**.
# Hai lib do khong duoc khai bao trong POM, app consume phai tu them:
#   implementation("com.google.code.gson:gson:2.10.1")
#   implementation("com.hivemq:hivemq-mqtt-client:1.3.15")
# De R8 bao loi luc build con hon app crash luc chay.

# --- Optional dependency cua Netty (transitive tu hivemq-mqtt-client) ---
# Lo ra sau khi keep rgbase/rgbaseapp: netty khong con bi shrink nen R8 thay het.
# epoll = native transport Linux, tcnative = OpenSSL binding,
# codec.http + websocketx = transport MQTT-over-WebSocket.
# Android khong dung nhanh nao trong so nay.
-dontwarn io.netty.channel.epoll.Epoll
-dontwarn io.netty.channel.epoll.EpollEventLoopGroup
-dontwarn io.netty.channel.epoll.EpollSocketChannel
-dontwarn io.netty.handler.codec.http.FullHttpResponse
-dontwarn io.netty.handler.codec.http.HttpClientCodec
-dontwarn io.netty.handler.codec.http.HttpHeaders
-dontwarn io.netty.handler.codec.http.HttpObjectAggregator
-dontwarn io.netty.handler.codec.http.HttpRequest
-dontwarn io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame
-dontwarn io.netty.handler.codec.http.websocketx.CloseWebSocketFrame
-dontwarn io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame
-dontwarn io.netty.handler.codec.http.websocketx.PingWebSocketFrame
-dontwarn io.netty.handler.codec.http.websocketx.PongWebSocketFrame
-dontwarn io.netty.handler.codec.http.websocketx.TextWebSocketFrame
-dontwarn io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker
-dontwarn io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory
-dontwarn io.netty.handler.codec.http.websocketx.WebSocketFrame
-dontwarn io.netty.handler.codec.http.websocketx.WebSocketHandshakeException
-dontwarn io.netty.handler.codec.http.websocketx.WebSocketVersion
-dontwarn io.netty.handler.proxy.HttpProxyHandler
-dontwarn io.netty.handler.proxy.ProxyHandler
-dontwarn io.netty.handler.proxy.Socks4ProxyHandler
-dontwarn io.netty.handler.proxy.Socks5ProxyHandler
-dontwarn io.netty.internal.tcnative.AsyncSSLPrivateKeyMethod
-dontwarn io.netty.internal.tcnative.AsyncTask
-dontwarn io.netty.internal.tcnative.Buffer
-dontwarn io.netty.internal.tcnative.CertificateCallback
-dontwarn io.netty.internal.tcnative.CertificateCompressionAlgo
-dontwarn io.netty.internal.tcnative.CertificateVerifier
-dontwarn io.netty.internal.tcnative.Library
-dontwarn io.netty.internal.tcnative.SSL
-dontwarn io.netty.internal.tcnative.SSLContext
-dontwarn io.netty.internal.tcnative.SSLPrivateKeyMethod
-dontwarn io.netty.internal.tcnative.SSLSessionCache
-dontwarn io.netty.internal.tcnative.SessionTicketKey
-dontwarn io.netty.internal.tcnative.SniHostNameMatcher

# Netty logging adapter cho log4j - Android khong co log4j.
-dontwarn org.apache.log4j.Level
-dontwarn org.apache.log4j.Logger
-dontwarn org.apache.log4j.Priority
-dontwarn org.apache.logging.log4j.Level
-dontwarn org.apache.logging.log4j.LogManager
-dontwarn org.apache.logging.log4j.Logger
-dontwarn org.apache.logging.log4j.message.MessageFactory
-dontwarn org.apache.logging.log4j.spi.ExtendedLogger
-dontwarn org.apache.logging.log4j.spi.ExtendedLoggerWrapper

# ALPN/NPN cua Jetty - chi dung tren JVM cu, Android dung provider he thong.
-dontwarn org.eclipse.jetty.alpn.ALPN$ClientProvider
-dontwarn org.eclipse.jetty.alpn.ALPN$Provider
-dontwarn org.eclipse.jetty.alpn.ALPN$ServerProvider
-dontwarn org.eclipse.jetty.alpn.ALPN
-dontwarn org.eclipse.jetty.npn.NextProtoNego$ClientProvider
-dontwarn org.eclipse.jetty.npn.NextProtoNego$Provider
-dontwarn org.eclipse.jetty.npn.NextProtoNego$ServerProvider
-dontwarn org.eclipse.jetty.npn.NextProtoNego

# rgbaseapp.H tham chieu class nay nhung khong jar nao trong libs/ chua no.
# Dangling reference co san trong Core - can chu so huu rogobaseapp kiem tra lai.
-dontwarn rogo.iot.module.utils.ByteUtil
