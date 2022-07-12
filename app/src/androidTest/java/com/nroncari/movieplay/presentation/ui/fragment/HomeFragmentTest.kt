package com.nroncari.movieplay.presentation.ui.fragment

import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import com.nroncari.movieplay.R
import com.nroncari.movieplay.utils.FileReader.readStringFromFile
import com.nroncari.movieplay.utils.launchFragment
import com.nroncari.movieplay.utils.onView
import com.nroncari.movieplay.utils.verify
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeFragmentTest {

    private val server = MockWebServer()
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun createServer() = server.start(serverPort)

    @After
    fun stopServer() = server.shutdown()

    @Test
    fun shouldDisplayTitle() {
        server.dispatcher = getDispatcher(successResponse)

        launchFragment<HomeFragment>()

        val expectedTitle = context.getString(R.string.title_home_label)

        ViewMatchers.withText(expectedTitle).onView { verify(ViewMatchers.isDisplayed()) }
    }

    @Test
    fun shouldDisplayErrorWhenListReturnNullId() {
        server.dispatcher = getDispatcher(errorResponse)

        launchFragment<HomeFragment>().apply {
            withId(R.id.network_error_animation).onView { verify(ViewMatchers.isDisplayed()) }
        }
    }

    private fun getDispatcher(response: MockResponse) = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return when (request.path) {
                "/discover/movie" -> response
                else -> errorResponse
            }
        }
    }

    companion object {
        private const val serverPort = 8080
        private val errorResponse by lazy { MockResponse().setResponseCode(404) }

        private val successResponse by lazy {
            mockResponse("okhttp/success_response.json")
        }

        private fun mockResponse(file: String) = MockResponse()
            .setResponseCode(200)
            .setBody(readStringFromFile(file))
    }
}