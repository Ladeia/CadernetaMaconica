package com.antonioladeia.cadernetamaconica.ui.session

import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.antonioladeia.cadernetamaconica.CadernetaTopAppBar
import com.antonioladeia.cadernetamaconica.R
import com.antonioladeia.cadernetamaconica.ui.navigation.NavigationDestination
import com.antonioladeia.cadernetamaconica.ui.theme.CadernetaMaconicaTheme
import kotlinx.coroutines.launch

object SessionEditDestination : NavigationDestination {
    override val route = "session_edit"
    override val titleRes = R.string.edit_session_title
    const val itemIdArg = "id"
    val routeWithArgs = "$route/{$itemIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            CadernetaTopAppBar(
                title = stringResource(SessionEditDestination.titleRes),
                canNavigateBack = true,
                navigateUp = onNavigateUp
            )
        },
        modifier = modifier
    ) { innerPadding ->
        SessionEntryBody(
            onSaveClick = {
                coroutineScope.launch {
                    { }
                    navigateBack()
                }
            },
            onItemValueChange = {},
            modifier = Modifier
                .padding(
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    top = innerPadding.calculateTopPadding(),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current),
                )
                .verticalScroll(rememberScrollState()),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemEditScreenPreview() {
    CadernetaMaconicaTheme {
        SessionEditScreen(navigateBack = { /*Do nothing*/ }, onNavigateUp = { /*Do nothing*/ })
    }
}
