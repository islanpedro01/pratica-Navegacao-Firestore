package com.example.navegacao1.ui.telas

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.navegacao1.model.dados.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



@Composable
fun TelaCadastro(modifier: Modifier = Modifier, onLoginClick: () -> Unit, onSignUpClick: () -> Unit) {
    var scope = rememberCoroutineScope()
    val context = LocalContext.current

    var nome by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var usuario = Usuario("", "")
    var mensagemErro by remember { mutableStateOf<String?>(null) }

    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "CADASTRO")
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text(text = "Nome") })
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(value = senha, visualTransformation = PasswordVisualTransformation(),
            onValueChange = { senha = it }, label = { Text(text = "Senha") })

        Row(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(modifier = modifier.size(125.dp, 50.dp), onClick = onLoginClick) {
                Text("Voltar")
            }
            Button(modifier = modifier.size(125.dp, 50.dp), onClick = {
                usuario.nome = nome
                usuario.senha = senha
                scope.launch(Dispatchers.IO) {
                    usuarioDAO.adicionar(usuario, callback = { usuario ->
                        if (nome.isNotEmpty() and senha.isNotEmpty()) {
                            onSignUpClick()
                        } else {
                            mensagemErro = "Nome e senha não podem estar em branco!"
                        }
                    })

                }

            }) {
                Text("Cadastrar")
            }
            mensagemErro?.let {
                LaunchedEffect(it) {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                    mensagemErro = null
                }
            }
        }
    }
    }




