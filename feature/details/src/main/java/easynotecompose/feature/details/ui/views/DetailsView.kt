package easynotecompose.feature.details.ui.views

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dirkeisold.easynotecompose.core.extension.takeIfNotEmpty
import com.dirkeisold.easynotecompose.core.model.Note
import com.dirkeisold.easynotecompose.core.ui.common.DevicePreviews
import com.dirkeisold.easynotecompose.design.component.AppBackground
import com.dirkeisold.easynotecompose.design.theme.MyTheme
import easynotecompose.feature.details.R
import easynotecompose.feature.details.common.createRandomNote
import easynotecompose.feature.details.ui.DetailsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsView(
    modifier: Modifier = Modifier,
    state: DetailsViewModel.DetailsUiState.Show,
    onBackClick: () -> Unit = {},
    onEditClicked: () -> Unit = {}
) {
    Column(modifier = modifier) {
        TopAppBar(
            title = {
                Text(
                    text = state.title,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleLarge,
                    overflow = TextOverflow.Ellipsis,
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
                    onClick = { onEditClicked() })
                {
                    Icon(
                        imageVector = Icons.Rounded.Edit,
                        contentDescription = stringResource(R.string.details_action_edit)
                    )
                }
            }
        )
        state.text?.takeIfNotEmpty {
            BoxWithConstraints {
                val maxHeight = this.maxHeight - 32.dp
                Column(
                    modifier = modifier
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {

                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = modifier
                            .height(maxHeight)
                            .fillMaxWidth()
                            .padding(16.dp),
                    ) {
                        Text(
                            modifier = modifier
                                .padding(16.dp),
                            text = it,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
        }
    }
}

@DevicePreviews
@Composable
internal fun DetailsViewPreview() {
    MyTheme {
        AppBackground {
            DetailsView(state = createRandomNote(1, longText = 200).toDetailsUiStateShow())
        }
    }
}

internal fun Note.toDetailsUiStateShow() =
    DetailsViewModel.DetailsUiState.Show(title = this.title, text = this.text)