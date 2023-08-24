import android.content.Context
import android.webkit.WebView
import android.webkit.WebViewClient

class WebViewIntegration(private val context: Context, private val webView: WebView) {

    init {
        setupWebView()
    }

    private fun setupWebView() {
        webView.webViewClient = WebViewClient()
        webView.settings.javaScriptEnabled = true
    }

    fun loadUrl(url: String) {
        webView.loadUrl(url)
    }
}
