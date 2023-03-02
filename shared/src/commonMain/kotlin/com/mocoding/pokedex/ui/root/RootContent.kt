package com.mocoding.pokedex.ui.root

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.fade
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.mocoding.pokedex.ui.details.DetailsContent
import com.mocoding.pokedex.ui.main.MainContent

@Composable
internal fun RootContent(component: RootComponent) {
    Children(
        stack = component.childStack,
        animation = stackAnimation(fade()),
    ) {
        it
        when(val child = it.instance) {
            is RootComponent.Child.Main -> MainContent(child.component)
            is RootComponent.Child.Details -> DetailsContent(child.component)
        }
    }
}