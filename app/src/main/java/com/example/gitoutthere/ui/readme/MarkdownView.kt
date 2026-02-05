package com.example.gitoutthere.ui.readme

import androidx.compose.runtime.Composable
import com.halilibo.richtext.markdown.Markdown
import com.halilibo.richtext.ui.material3.RichText

@Composable
fun MarkdownView(markdown: String) {
    RichText {
        Markdown(content = markdown)
    }
}