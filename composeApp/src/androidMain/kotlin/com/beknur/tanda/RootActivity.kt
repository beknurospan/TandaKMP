package com.beknur.tanda

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arkivanov.decompose.defaultComponentContext
import com.beknur.tanda.navigation.root.DefaultRootComponent
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RootActivity : ComponentActivity(), KoinComponent {

    private val rootFactory by inject<DefaultRootComponent.Factory>()
    private val root by lazy {
        rootFactory.create(defaultComponentContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {

            SausaqApp(root)
        }
    }
}



