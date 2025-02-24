package com.example.navegacao1.ui.telas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.navegacao1.model.dados.Usuario
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Composable
fun TelaPrincipal(modifier: Modifier = Modifier, onLogoffClick: () -> Unit) {
    var scope = rememberCoroutineScope()

    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "TELA PRINCIPAL")
        val usuarios = remember { mutableStateListOf<Usuario>() }

        Button(modifier = modifier.size(125.dp, 50.dp), onClick = {
            scope.launch(Dispatchers.IO) {
                usuarioDAO.buscar( callback = { usuariosRetornados ->
                    usuarios.clear()
                    usuarios.addAll(usuariosRetornados)
                })
            }
        }) {
            Text("Carregar")
        }
        Button(modifier = modifier.size(125.dp, 50.dp),onClick = { onLogoffClick() }) {
            Text("Sair")
        }

        //Carrega sob demanda à medida que o usuário rola na tela
        LazyColumn {
            items(usuarios) { usuario ->
                Card(modifier = Modifier.size(200.dp, 200.dp).padding(5.dp)) {
                    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
                        Text(text = usuario.nome)
                    }
                }
            }
        }
    }

}