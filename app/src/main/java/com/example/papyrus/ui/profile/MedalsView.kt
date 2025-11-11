/*
package com.example.papyrus.ui.profile

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import com.example.papyrus.data.entities.MedalEntity

@Composable
fun MedalsView(medals: List<MedalEntity> = emptyList()) {
    Column(Modifier.padding(16.dp)) {
        Text("Your Medals", style = MaterialTheme.typography.titleMedium)
        LazyColumn {
            items(medals) { medal ->
                Card(Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                    Column(Modifier.padding(8.dp)) {
                        Text(medal.name, style = MaterialTheme.typography.titleSmall)
                        Text(medal.description, style = MaterialTheme.typography.bodySmall)
                        Text("Earned: ${medal.dateEarned}", style = MaterialTheme.typography.bodySmall)
                    }
                }
            }
        }
    }
}*/
