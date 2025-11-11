/*
package com.example.papyrus.ui.profile

import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ProfileView(
    profile: UserProfileEntity? = null,
    onSave: (UserProfileEntity) -> Unit = {}
) {
    var name by remember { mutableStateOf(profile?.name ?: "") }
    var email by remember { mutableStateOf(profile?.email ?: "") }
    var annualTarget by remember { mutableStateOf(profile?.annualBookTarget?.toString() ?: "") }

    Column(Modifier.padding(16.dp)) {
        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = annualTarget,
            onValueChange = { annualTarget = it.filter { c -> c.isDigit() } },
            label = { Text("Annual Book Target") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            val updated = UserProfileEntity(name = name, email = email, annualBookTarget = annualTarget.toIntOrNull() ?: 0)
            onSave(updated)
        }, modifier = Modifier.align(Alignment.End)) {
            Text("Save")
        }
    }
}*/
