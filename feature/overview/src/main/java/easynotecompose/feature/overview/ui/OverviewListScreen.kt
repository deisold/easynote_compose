package easynotecompose.feature.overview.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dirkeisold.easynotecompose.core.model.Note

@Composable
fun OverviewListScreen(
    modifier: Modifier = Modifier,
    notes: Iterable<Note>,
    navigateToDetails: (String) -> Unit,
) {
    LazyColumn(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
        contentPadding = PaddingValues(top = 8.dp)
    ) {
        notes.forEach { note ->
            item(key = note.id) {
                OverviewListItem(
                    id = note.id,
                    title = note.title,
                    text = note.text,
                    modifier = modifier,
                    navigateToDetails = navigateToDetails
                )
            }
        }
    }
}


