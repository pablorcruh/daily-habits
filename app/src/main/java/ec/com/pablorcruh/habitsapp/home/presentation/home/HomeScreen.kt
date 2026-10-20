package ec.com.pablorcruh.habitsapp.home.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import ec.com.pablorcruh.habitsapp.R
import ec.com.pablorcruh.habitsapp.home.presentation.home.components.HomeDateSelector
import ec.com.pablorcruh.habitsapp.home.presentation.home.components.HomeQuote
import java.time.ZonedDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
){
    val state = viewModel.state
    Scaffold(modifier = Modifier.fillMaxSize(), topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = "Home") },
            navigationIcon = {
                IconButton(
                    onClick = {}
                ) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = "settings")
                }
            })
    }) {
        Column(modifier = Modifier.padding(it).padding(20.dp), verticalArrangement = Arrangement.spacedBy(19.dp)
        ) {
            HomeQuote(
                quote = "we first make our habits, and then our habits make us",
                author = "Annonymous",
                imageId = R.drawable.onboarding1
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = "habits".uppercase(),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary
                )
                Spacer(modifier = Modifier.width(16.dp))
                HomeDateSelector(
                    selectedDate = state.selectedDate,
                    mainDate = state.currentDate,
                    onDateClick = {
                        viewModel.onEvent(HomeEvent.ChangeDate(it))
                    }
                )
            }
        }
    }
}