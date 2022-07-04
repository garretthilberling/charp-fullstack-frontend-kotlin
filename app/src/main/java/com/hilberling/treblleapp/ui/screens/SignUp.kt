package com.hilberling.treblleapp.ui.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SignUp() {
    val toggleState = remember {
        mutableStateOf(false) // means it can be overridden. defaults to false
    }

    val passwordState = remember {
        mutableStateOf("") // defaults to empty string
    }

    val emailState = remember {
        mutableStateOf("") // defaults to empty string
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        OutlinedTextField(value = emailState.value, onValueChange = {
            emailState.value = it
        }, modifier = Modifier.fillMaxWidth(),
            label = {Text("Email Address")},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        OutlinedTextField(value = passwordState.value, onValueChange = {
            emailState.value = it
        }, modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
            label = {Text("Password")},
            trailingIcon = {
                IconButton(onClick = {
                    toggleState.value = !toggleState.value // will toggle between true and false
                }) {

                }
            }
        )
    }
}