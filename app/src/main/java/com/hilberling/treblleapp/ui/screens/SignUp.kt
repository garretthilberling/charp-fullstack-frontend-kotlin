package com.hilberling.treblleapp.ui.screens
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hilberling.treblleapp.data.RegisterUser
import com.hilberling.treblleapp.data.Resource
import com.hilberling.treblleapp.ui.viewmodel.MainViewModel

@Composable
fun SignUp(viewModel: MainViewModel) {

    val registerState = viewModel.registerRequestState.collectAsState().value

    val context = LocalContext.current

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
                    Icon(
                        imageVector = if(toggleState.value) Icons.Default.Visibility else
                            Icons.Default.VisibilityOff,
                        contentDescription = ""
                    )
                }
            },
            visualTransformation = if(toggleState.value) VisualTransformation.None else
                PasswordVisualTransformation()
        )
        // signup button
        Button(onClick = {
            val registerUser = RegisterUser(email = emailState.value,
            password = passwordState.value, password_confirmation = passwordState.value)
            viewModel.registerUser(registerUser = registerUser)
        }, modifier = Modifier.fillMaxWidth()) {
           Text(text = "Sign Up", modifier = Modifier.padding(vertical = 8.dp))
        }
        Divider(modifier = Modifier.padding(top = 16.dp))
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
            horizontalArrangement = Arrangement.Center) {
            Text(text = "Already have an account?")
            Text(text = "Login", fontWeight = FontWeight.Bold)
        }

        when(registerState) {
            is Resource.Loading -> {
                Toast.makeText(context, "Registration in progress", Toast.LENGTH_LONG)
                    .show()
            }
            is Resource.Success -> {
                Toast.makeText(context, "User successfully registered", Toast.LENGTH_LONG)
                    .show()
            }
            is Resource.Error -> {
                Toast.makeText(context, "An error has occurred", Toast.LENGTH_LONG)
                    .show()
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    SignUp(viewModel())
}