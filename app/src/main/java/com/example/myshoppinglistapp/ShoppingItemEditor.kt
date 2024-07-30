package com.example.myshoppinglistapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsEndWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingItemEditor(item: ShoppingItem,
                       onEditComlete: (String, Int) -> Unit,
                        onDeleteClick: () -> Unit) {
    var editedName by remember { mutableStateOf(item.name) }
    var editedQuantity by remember { mutableStateOf(item.quantity.toString()) }
    var isEditing by remember { mutableStateOf(item.isEditing) }

    Box(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .background(color = Color.White, shape = RoundedCornerShape(10))
        .border(
            border = BorderStroke(2.dp, Color(0XFF018786)),
            shape = RoundedCornerShape(10)
        ))
    {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.Transparent) // No background color here
                    .border(
                        border = BorderStroke(2.dp, Color(0XFF018786)),
                        shape = RoundedCornerShape(20)
                    )
                    .clip(RoundedCornerShape(20)) // Make sure the border shape is correct
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.LightGray, shape = RoundedCornerShape(20)),
                    value = editedName,
                    onValueChange = { editedName = it },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black
                    )
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.Transparent) // No background color here
                    .border(
                        border = BorderStroke(2.dp, Color(0XFF018786)),
                        shape = RoundedCornerShape(20)
                    )
                    .clip(RoundedCornerShape(20)) // Make sure the border shape is correct
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = Color.LightGray, shape = RoundedCornerShape(20)),
                    value = editedQuantity,
                    onValueChange = { editedQuantity = it },
                    singleLine = true,
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black
                    ))}
            Spacer(modifier = Modifier.weight(1f))
            Row (modifier = Modifier.fillMaxSize()
                .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.End){
                Button(
                    onClick = {
                        isEditing = false
                        onEditComlete(editedName, editedQuantity.toIntOrNull() ?: 1)
                    },
                    colors = ButtonDefaults.buttonColors(Color(0XFF018786)),
                ){
                    Text(text = "Save")
                }
                Spacer(modifier = Modifier.width(20.dp))
                Box(modifier = Modifier
                    .padding(4.dp)
                    .size(38.dp)
                    .wrapContentSize()
                    .background(color = Color(0XFF018786), shape = RoundedCornerShape(10))
                    .border(
                        border = BorderStroke(2.dp, Color(0XFF018786)),
                        shape = RoundedCornerShape(10)
                    )){

                    IconButton(onClick = onDeleteClick) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
