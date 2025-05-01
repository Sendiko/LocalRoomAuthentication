package com.github.sendiko.localroomauthentication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.sendiko.localroomauthentication.login.LoginViewModel
import com.github.sendiko.localroomauthentication.ui.theme.LocalRoomAuthenticationTheme
import com.github.sendiko.localroomauthentication.util.ViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LocalRoomAuthenticationTheme {

                val context = LocalContext.current
                val factory = ViewModelFactory(context)
                val viewModel: LoginViewModel = viewModel(factory = factory)
                val pesan by viewModel.pesan.collectAsStateWithLifecycle()

                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) { innerPadding ->
                    AnimatedVisibility(pesan.isNotBlank()) {
                        Text(pesan)
                    }
                    Column(
                        modifier = Modifier.fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
                    ) {
                        OutlinedTextField(
                            value = email,
                            onValueChange = { text ->
                                email = text
                            }
                        )

                        OutlinedTextField(
                            value = password,
                            onValueChange = { text ->
                                password = text
                            }
                        )

                        Button(
                            onClick = {  }
                        ) { }
                    }
                }
            }
        }
    }
}