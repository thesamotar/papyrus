//package com.example.papyrus.ui.library
//
//import androidx.compose.material3.*
//import androidx.compose.runtime.*
//import com.example.papyrus.data.LibraryManagement.Book
//import com.example.papyrus.data.LibraryManagement.Shelf
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//
//@Composable
//fun AddToShelfDialog(
//    book: Book,
//    shelves: List<Shelf>,
//    onAdd: (shelf: Shelf) -> Unit,
//    onDismiss: () -> Unit
//) {
//    var selectedShelfId by remember { mutableStateOf(shelves.firstOrNull()?.id ?: "") }
//    AlertDialog(
//        onDismissRequest = onDismiss,
//        confirmButton = {
//            Button(onClick = {
//                shelves.find { it.id == selectedShelfId }?.let { shelf ->
//                    onAdd(shelf)
//                    onDismiss()
//                }
//            }) { Text("Add") }
//        },
//        dismissButton = {
//            TextButton(onClick = onDismiss) { Text("Cancel") }
//        },
//        title = { Text("Add '${book.title}' to shelf") },
//        text = {
//            Column {
//                shelves.forEach { shelf ->
//                    Row {
//                        RadioButton(
//                            selected = selectedShelfId == shelf.id,
//                            onClick = { selectedShelfId = shelf.id }
//                        )
//                        Text(shelf.name)
//                    }
//                }
//            }
//        }
//    )
//}