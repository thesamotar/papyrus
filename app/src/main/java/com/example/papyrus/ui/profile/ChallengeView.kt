package com.example.papyrus.ui.profile

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChallengeView(
    target: Int = 0,
    readCount: Int = 0
) {
    val progress = if (target > 0) readCount.toFloat() / target else 0f
    Column(Modifier.padding(16.dp)) {
        Text("Annual Reading Challenge", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        LinearProgressIndicator(progress = progress, modifier = Modifier.fillMaxWidth())
        Spacer(Modifier.height(8.dp))
        Text("$readCount / $target books read")
    }
}