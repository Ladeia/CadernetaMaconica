package com.antonioladeia.cadernetamaconica.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.antonioladeia.cadernetamaconica.CadernetaTopAppBar
import com.antonioladeia.cadernetamaconica.R
import com.antonioladeia.cadernetamaconica.data.SessionDataFake
import com.antonioladeia.cadernetamaconica.data.SessionEntity
import com.antonioladeia.cadernetamaconica.ui.navigation.NavigationDestination
import com.antonioladeia.cadernetamaconica.ui.theme.CadernetaMaconicaTheme
import java.time.LocalDate

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
//    navigateToItemUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val sessions = SessionDataFake().allSessions()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CadernetaTopAppBar(
                title = stringResource(HomeDestination.titleRes),
                canNavigateBack = false,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .padding(
                        end = WindowInsets.safeDrawing.asPaddingValues()
                            .calculateEndPadding(LocalLayoutDirection.current)
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.session_entry_title)
                )
            }
        },
    ) { innerPadding ->
        HomeBody(
            sessionList = sessions,
            //onSessionClick = doNothing(),
            modifier = modifier.fillMaxSize(),
            contentPadding = innerPadding,
        )
    }
}

@Composable
private fun HomeBody(
    sessionList: List<SessionEntity>,
    //onSessionClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        if (sessionList.isEmpty()) {
            Text(
                text = stringResource(R.string.no_session_description),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(contentPadding),
            )
        } else {
            SessionList(
                itemList = sessionList,
                onItemClick = { doNothing() },
                contentPadding = contentPadding,
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.padding_small))
            )
        }
    }
}

@Composable
private fun SessionList(
    itemList: List<SessionEntity>,
    onItemClick: (SessionEntity) -> Unit,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        items(items = itemList, key = { it.id }) { item ->
            SessionItem(item = item,
                modifier = Modifier
                    .padding(dimensionResource(id = R.dimen.padding_small))
                    .clickable { onItemClick(item) })
        }
    }
}

@Composable
private fun SessionItem(
    item: SessionEntity, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier, elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_large)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = item.sessao,
                    style = MaterialTheme.typography.titleLarge,
                )
            }
            Text(
                text = item.dataSessao,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeBodyPreview() {
    val sessions = SessionDataFake().allSessions()
    CadernetaMaconicaTheme {
        HomeBody(sessions)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeBodyEmptyListPreview() {
    CadernetaMaconicaTheme {
        HomeBody(listOf())
    }
}

@Preview(showBackground = true)
@Composable
fun SessionItemPreview() {
    CadernetaMaconicaTheme {
        SessionItem(
            SessionEntity(1, LocalDate.of(2025, 7, 30).toString(), "Sessão 01", "loja", "oriente", "potência", "rito", "observacoes"),
        )
    }
}

fun doNothing(){

}
