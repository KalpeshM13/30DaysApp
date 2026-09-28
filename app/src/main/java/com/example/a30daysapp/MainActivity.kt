package com.example.a30daysapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.autofill.ContentType
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.a30daysapp.model.Tips
import com.example.a30daysapp.model.TipsRepository.tips
import com.example.a30daysapp.ui.theme._30DaysAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _30DaysAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    A30DaysApp()
                }
            }
        }
    }

    @Composable
    fun A30DaysApp() {
        Scaffold(
            topBar = {
                TopAppBar()
            }
        ) {it ->
            LazyColumn(contentPadding = it) {
                items(tips) {
                    TipsListItem(
                        tips = it,
                        modifier = Modifier
                            .padding(16.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopAppBar(modifier: Modifier = Modifier) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge,
                )
            },
            modifier = modifier
        )
    }

    @Composable
    fun TipsListItem(
        tips: Tips,
        modifier: Modifier = Modifier
    ) {
        var expanded by remember { mutableStateOf(false) }

        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            modifier = modifier,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            )
        ) {
                Column(
                    modifier = Modifier
                        .animateContentSize(
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioNoBouncy,
                                stiffness = Spring.StiffnessMedium
                            )
                        )
                ) {
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .height(350.dp)
                    ) {
                        Text(
                            text = stringResource(tips.day),
                            style = MaterialTheme.typography.labelSmall,
                            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
                        )
                        Text(
                            text = stringResource(tips.title),
                            style = MaterialTheme.typography.displayMedium,
                            modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
                        )
                        TipItemButton(
                            modifier = Modifier
                                .fillMaxSize(),
                            image = tips.imageResourceId,
                            onClick = { expanded = !expanded }
                        )
                    }

                    if (expanded) {
                        Column(
                            modifier = modifier
                        ) {
                            Text(
                                text = stringResource(tips.description),
                                style = MaterialTheme.typography.displayMedium
                            )
                        }
                    }
            }
        }
    }

    @Composable
    private fun TipItemButton(
        onClick: () -> Unit,
        modifier: Modifier = Modifier,
        image: Int
    ) {
            Image(
                painterResource(image),
                contentDescription = stringResource(R.string.expand_button_content_description),
                modifier = Modifier
                    .clickable(onClick = onClick)
                    .padding(top = 5.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
    }

    @Preview(showSystemUi = true, showBackground = true)
    @Composable
    fun AppPreview() {
        _30DaysAppTheme {
            A30DaysApp()
        }
    }
}
