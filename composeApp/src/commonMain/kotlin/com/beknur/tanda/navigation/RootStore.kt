package com.beknur.tanda.navigation

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.beknur.tanda.navigation.RootStore.Intent
import com.beknur.tanda.navigation.RootStore.Label
import com.beknur.tanda.navigation.RootStore.State

interface RootStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data class OnClickTab(val bottomNavItem:AppTab) : Intent
    }

    data class State(
		val isBottomBarVisible: Boolean = true,
        val selectedTab: AppTab= AppTab.Catalog
	)

    sealed interface Label {
        data class ClickTab(val bottomNavItem: AppTab) : Label
    }
}

class RootStoreFactory(
    private val storeFactory: StoreFactory
) {

    fun create(): RootStore =
        object : RootStore, Store<Intent, State, Label> by storeFactory.create(
            name = "RootStore",
            initialState = State(),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
    }

    private sealed interface Msg {
        data class TabChanged(val bottomNavItem: AppTab) : Msg
    }

    private class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
        }
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent) {
            when(intent){


	            is Intent.OnClickTab -> {
                    dispatch(Msg.TabChanged(intent.bottomNavItem))
                    publish(Label.ClickTab(intent.bottomNavItem))
                }
            }
        }

        override fun executeAction(action: Action) {
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State {
            when(msg){
	            is Msg.TabChanged -> {
                    return copy(selectedTab = msg.bottomNavItem)
                }
            }
        }
    }
}
