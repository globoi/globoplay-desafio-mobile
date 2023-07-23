import androidx.compose.ui.focus.FocusRequester

fun FocusRequester.tryRequestFocus() {
    try {
        requestFocus()
    } catch (ignore: Exception) {
    }
}