package com.example.myshoppinglistapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myshoppinglistapp.ui.theme.MyShoppingListAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListApp(){
    var sItems by remember { mutableStateOf(listOf<ShoppingItem>()) }
    var showDialog by remember {
        mutableStateOf(false)
    }
    var itemName by remember {
        mutableStateOf("")
    }
    var itemQuantity by remember {
        mutableStateOf("")
    }

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFCFCFCF)),

    ){
        Column {
        // Heading
            Text(
                text = "Shopping List",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold, textDecoration = TextDecoration.Underline),
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp) // Vertical padding for better spacing
                    .background(Color(0xFF018786)) // Background color for the heading
                    .padding(16.dp) // Padding inside the background
                    .align(Alignment.CenterHorizontally)
                   // Center horizontally
            )


            LazyColumn(// fillmaxsize capture all the space present on screen which are empty
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(sItems) { item ->
                    if (item.isEditing) {
                        ShoppingItemEditor(item = item, onEditComlete = { editedName, editedQuantity ->
                            sItems = sItems.map { it.copy(isEditing = false) }
                            val editedItem = sItems.find { it.id == item.id }
                            editedItem?.let {
                                it.name = editedName
                                it.quantity = editedQuantity
                            }
                        },
                            onDeleteClick = {
                                sItems = sItems.filter { it.id != item.id }
                            }
                        )
                    } else {
                        ShoppingListItems(
                            item = item,
                            onEditClick = {
                                sItems = sItems.map { it.copy(isEditing = it.id == item.id) }
                            },
                            onDeleteClick = { sItems = sItems.filter { it.id != item.id } }

                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .size(104.dp)
                .align(Alignment.BottomEnd)
                .padding(bottom = 35.dp, end = 25.dp)
                .clickable(onClick = { showDialog = true })


        ) { // Adjust size as needed
            Image(
                painter = painterResource(id = R.drawable.add_collection_svgrepo_com), // Replace with your drawable name
                contentDescription = "Plus Sign Icon"
            )
        }
    }
    if (showDialog){
        AlertDialog(onDismissRequest = { showDialog = false },
            confirmButton = {
                Row (modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                    ){
                    Button(onClick = {
                        if(itemName.isNotBlank()){

                            val newItem = ShoppingItem(
                                id = sItems.size+1,
                                name = itemName,

                                quantity = itemQuantity.toInt()
                                )
                            sItems = sItems + newItem
                            showDialog = false
                            itemName = ""
                            itemQuantity = ""// here item name will automatically fill when entered to add item

                        }

                    },
                        colors = ButtonDefaults.buttonColors(Color(0XFF018786)))
                    {
                        Text(text = "Add")
                    }
                    Button(
                        onClick = { showDialog = false},
                        colors = ButtonDefaults.buttonColors(Color(0XFF018786))
                        ) {
                        Text(text = "Cancle")
                    }
                }
            },
            title = { Text(text = "Add Shopping Item")},
            text = {
                Column {
                    TextField(
                        placeholder = { Text("Enter Item") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(color = Color.LightGray)
                            .border(
                                border = BorderStroke(2.dp, Color(0XFF018786)),
                                shape = RoundedCornerShape(20)
                            ),
                        value = itemName,
                        onValueChange ={ itemName = it },
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent, // Hide the indicator color when focused
                            unfocusedIndicatorColor = Color.Transparent, // Hide the indicator color when not focused
                            cursorColor = Color.Black // Optionally customize cursor color
                        )
                        )
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(color = Color.LightGray)
                            .border(
                                border = BorderStroke(2.dp, Color(0XFF018786)),
                                shape = RoundedCornerShape(20)
                            ),
                        placeholder = { Text("Enter Quantity") },
                        value = itemQuantity,
                        onValueChange ={ itemQuantity = it },
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent, // Hide the indicator color when focused
                            unfocusedIndicatorColor = Color.Transparent, // Hide the indicator color when not focused
                            cursorColor = Color.Black // Optionally customize cursor color
                        ),
                    )

                }
            })
    }
}