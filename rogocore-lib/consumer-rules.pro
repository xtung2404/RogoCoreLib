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
