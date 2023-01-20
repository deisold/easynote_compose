package easynotecompose.feature.overview.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dirkeisold.easynotecompose.core.extension.takeIfNotEmpty
import com.dirkeisold.easynotecompose.design.theme.MyTheme

@Composable
fun OverviewListItem(
    modifier: Modifier = Modifier,
    id: String,
    title: String,
    text: String? = null,
    itemSeparation: Dp = 8.dp,
    navigateToDetails: (String) -> Unit,
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { navigateToDetails(id) }
    ) {
        Row(
            modifier = modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(modifier = modifier.weight(1f)) {
                Column(
                    modifier = Modifier
                        .heightIn(48.dp, 80.dp)
                        .padding(vertical = itemSeparation),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = title,
                        maxLines = 1,
                        style = MaterialTheme.typography.titleMedium,
                        overflow = TextOverflow.Ellipsis,
                    )
                    text?.takeIfNotEmpty {
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = it,
                            maxLines = 3,
                            style = MaterialTheme.typography.bodyMedium,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun OverviewListItemPreview() {
    MyTheme {
        Surface {
            OverviewListItem(
                id = "1",
                title = "Test title",
                navigateToDetails = {})
        }
    }
}

@Preview
@Composable
fun OverviewListItemPreviewLongTitle() {
    MyTheme {
        Surface {
            OverviewListItem(
                id = "1",
                title = "Test title verrrry long kljsald;fj a;s f;lajflksjjd flkjfsad flskafd",
                text = "Text verrrry long kljsald;fj a;s f;lajflksjjd flkjfsad flskafd",
                navigateToDetails = {}
            )
        }
    }
}