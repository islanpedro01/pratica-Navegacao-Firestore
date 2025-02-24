package com.example.navegacao1.model.dados

import com.google.firebase.firestore.DocumentId

data class Usuario(
    //TODO defina uma propriedade chamada Id, de tipo String
    @DocumentId
    val id: String = "",
    var nome: String = "",
    var senha: String = ""
)