package telstra.sohan.com.sohantelstracodingtest.utility

import java.io.IOException

class Constants {

    companion object {
        const val BASE_URL = "https://dl.dropboxusercontent.com"

        const val FACTS_API = "/s/2iodh4vg0eortkl/facts.json"

        // ICMP
        fun isOnline(): Boolean {
            val runtime = Runtime.getRuntime()
            try {
                val ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8")
                val exitValue = ipProcess.waitFor()
                return exitValue == 0
            } catch (e: IOException) {
                e.printStackTrace()
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }

            return false
        }
    }


}