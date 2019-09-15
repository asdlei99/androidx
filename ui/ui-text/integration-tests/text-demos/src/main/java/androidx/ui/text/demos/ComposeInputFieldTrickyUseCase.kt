/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.ui.text.demos

import androidx.compose.composer
import androidx.compose.Composable
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.core.TextField
import androidx.ui.core.sp
import androidx.ui.layout.Column
import androidx.ui.layout.CrossAxisAlignment
import androidx.ui.foundation.VerticalScroller
import androidx.ui.input.EditorModel
import androidx.ui.input.EditorStyle
import androidx.ui.input.KeyboardType
import androidx.ui.text.TextStyle

@Composable
fun InputFieldTrickyUseCase() {
    VerticalScroller {
        Column(crossAxisAlignment = CrossAxisAlignment.Start) {
            TagLine(tag = "don't set if non number is added")
            RejectNonDigits()

            TagLine(tag = "always clear composition")
            RejectComposition()
        }
    }
}

@Composable
private fun RejectNonDigits() {
    val state = +state { EditorModel() }
    TextField(
        value = state.value,
        editorStyle = EditorStyle(textStyle = TextStyle(fontSize = 32.sp)),
        onValueChange = {
            if (it.text.all { it.isDigit() }) {
                state.value = it
            }
        },
        keyboardType = KeyboardType.Number
    )
}

@Composable
private fun RejectComposition() {
    val state = +state { EditorModel() }
    TextField(
        value = state.value,
        editorStyle = EditorStyle(textStyle = TextStyle(fontSize = 32.sp)),
        onValueChange = {
            state.value = EditorModel(text = it.text, selection = it.selection)
        }
    )
}