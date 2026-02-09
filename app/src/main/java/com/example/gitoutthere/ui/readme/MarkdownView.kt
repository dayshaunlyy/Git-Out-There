package com.example.gitoutthere.ui.readme

import androidx.compose.runtime.Composable
import com.halilibo.richtext.markdown.Markdown
import com.halilibo.richtext.ui.material3.RichText

@Composable
fun MarkdownView(markdown: String) {
    // Regex to find and remove markdown image tags
    val mdRegex = """\[!\[.*?]\(.*?\)](?:\(.*?\))?""".toRegex()
    val mdSanitizedMarkdown = markdown.replace(mdRegex, "")

    val htmlRegex = """(?is)<a\b[^>]*>\s*<img\b[^>]*>\s*</a>""".toRegex()
    val sanitizedMarkdown = mdSanitizedMarkdown.replace(htmlRegex, "")

    RichText {
        Markdown(content = sanitizedMarkdown)
    }
}
