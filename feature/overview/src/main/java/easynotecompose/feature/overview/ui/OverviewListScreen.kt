package easynotecompose.feature.overview.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dirkeisold.easynotecompose.core.model.Note
import easynotecompose.feature.overview.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverviewListScreen(
    modifier: Modifier = Modifier,
    notes: Iterable<Note>,
    navigateToDetails: (String) -> Unit,
) {
    Column {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.overview_appbar_title),
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        )
        LazyColumn(
            modifier = modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
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
}


