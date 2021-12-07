package com.example.acromineapp

import com.example.acromineapp.network.AcronymApi
import createTestApi
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

class AcronymApiTest {

    @get:Rule
    val server = MockWebServer()
    val subject: AcronymApi = createTestApi(server)

    @Test
    fun `acronym api get successfully parsed from response`() {
        // given
        server.enqueue(
            MockResponse().apply {
                setResponseCode(200)
                setBody(
                    """
                       [
                         {
                          "sf": "WHO", 
                          "lfs": [
                          {
                            "lf": "World Health Organization", 
                            "freq": 4485, 
                            "since": 1951, 
                            "vars": [
                             {
                               "lf": "World Health Organization", 
                                "freq": 4122, 
                                "since": 1956
                             }, 
                             {
                               "lf": "World Health Organisation", 
                               "freq": 325, 
                               "since": 1977
                              }, 
                              { 
                                "lf": "world health organization", 
                                "freq": 8, 
                                "since": 1995
                              }, 
                              {
                                "lf": "World Heath Organization", 
                                "freq": 8, 
                                "since": 1995
                              }, 
                              {
                                "lf": "World health organization", 
                                "freq": 6, 
                                "since": 1951
                              }, 
                              {
                                "lf": "World-Health-Organization", 
                                "freq": 1, 
                                "since": 1984
                                }
                             ]
                          }
                         ]
                       }
                      ]
                    """.trimIndent()
                )
            }
        )

        // when
        val response = subject.getShortAcronymDetails("who").execute()

        // then
        assert(response.isSuccessful)
        assertEquals(response.body()?.get(0)?.sf,"WHO")
        assertNotNull(response.body()?.get(0)?.lfs)
    }
}