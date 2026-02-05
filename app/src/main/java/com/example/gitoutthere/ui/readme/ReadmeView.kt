package com.example.gitoutthere.ui.readme

import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun ReadmeView(url: String) {
    AndroidView(factory = {
        WebView(it).apply {
            loadUrl(url)
        }
    })
}
