package easynotecompose.feature.details.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dirkeisold.easynotecompose.core.model.Note
import com.dirkeisold.easynotecompose.core.ui.common.DevicePreviews
import com.dirkeisold.easynotecompose.design.component.AppBackground
import com.dirkeisold.easynotecompose.design.theme.MyTheme
import easynotecompose.feature.details.R
import easynotecompose.feature.details.common.createRandomNote
import easynotecompose.feature.details.model.UpdatedNote
import easynotecompose.feature.details.ui.details.DetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsEditView(
    modifier: Modifier = Modifier,
    title: String, text: String?,
    onBackClick: () -> Unit,
    onSaveClicked: (UpdatedNote) -> Unit,
) {

    var titleState by remember { mutableStateOf(title) }
    var textState by remember { mutableStateOf(text ?: "") }

    Column(modifier = modifier) {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.details_action_edit_details_title),
                    style = MaterialTheme.typography.titleLarge,
                )
            },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(id = R.string.details_action_back),
                    )
                }
            },
            actions = {
                IconButton(
                    modifier = modifier
                        .size(40.dp)
                        .padding(8.dp),
                    onClick = {
                        onSaveClicked(
                            UpdatedNote(
                                title = titleState,
                                text = textState
                            )
                        )
                    })
                {
                    Icon(
                        imageVector = Icons.Rounded.Save,
                        contentDescription = stringResource(R.string.details_action_edit)
                    )
                }
            }
        )
        Column(
            modifier = modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            OutlinedTextField(
                modifier = modifier,
                label = { Text(stringResource(id = R.string.details_action_edit_details_title_caption)) },
                value = titleState,
                onValueChange = { titleState = it }
            )


            OutlinedTextField(
                modifier = modifier,
                label = { Text(stringResource(id = R.string.details_action_edit_details_text_caption)) },
                value = textState,
                onValueChange = { textState = it }
            )
        }
    }
}

internal fun DetailsViewModel.DetailsUiState.Edit.toUpdatedNote() = UpdatedNote(
    title = this.title,
    text = this.text
)

@DevicePreviews
@Composable
internal fun DetailsEditPreview() {
    MyTheme {
        AppBackground {
            val note = createRandomNote(1, longText = 200).toDetailsUiStateEdit()
            DetailsEditView(
                title = note.title,
                text = note.text,
                onBackClick = {},
                onSaveClicked = {},
            )
        }
    }
}


internal fun Note.toDetailsUiStateEdit() =
    DetailsViewModel.DetailsUiState.Edit(title = this.title, text = this.text)