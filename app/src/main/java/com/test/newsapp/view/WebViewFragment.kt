import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.test.newsapp.R
import com.test.newsapp.util.Access

class WebViewFragment : Fragment(R.layout.fragment_web_view) {
    lateinit var webView: WebView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = view.findViewById(R.id.web_view)

        webView.webViewClient = WebViewClient()

        configureWebView()
        loadUrlFrom()
    }

    private fun loadUrlFrom() {
        val url = arguments?.get(Access.NEWS_BUNDLE_KEY).toString()
        webView.loadUrl(url)
    }


    @SuppressLint("SetJavaScriptEnabled")
    fun configureWebView() {
        webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
            cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        }
        CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true)
    }
}
