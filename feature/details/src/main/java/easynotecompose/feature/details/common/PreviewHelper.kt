package easynotecompose.feature.details.common

import com.dirkeisold.easynotecompose.core.model.Note
import java.util.Date

internal fun createRandomNote(index: Int, longText: Int = 1) = Note(
    id = (index + 1).toString(),
    created = Date(),
    title = "Note #${index + 1}",
    text = createRandomNoteText(index, longText)
)

internal fun createRandomNoteText(index: Int, longText: Int) = buildString {
    repeat(longText) {
        append("Text #${index + 1}")
    }
}