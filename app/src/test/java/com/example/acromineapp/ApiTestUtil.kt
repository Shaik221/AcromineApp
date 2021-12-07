import com.example.acromineapp.dagger.module.NetworkModule
import com.google.gson.Gson
import okhttp3.mockwebserver.MockWebServer

val sharedTestClient = Gson()
val networkModule = NetworkModule("http://www.test.com")
inline fun <reified T> createTestApi(mockWebServer: MockWebServer): T {
    return networkModule.provideRetrofit(
        sharedTestClient
    ).newBuilder()
        .baseUrl(mockWebServer.url("/"))
        .build()
        .create(T::class.java)
}
