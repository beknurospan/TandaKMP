package com.beknur.tanda.navigation.root

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.beknur.tanda.navigation.AppTab
import com.beknur.tanda.navigation.root.RootStore.Intent
import com.beknur.tanda.navigation.root.RootStore.Label
import com.beknur.tanda.navigation.root.RootStore.State
import kotlinx.coroutines.launch

interface RootStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data class OnClickTab(val bottomNavItem: AppTab) : Intent
    }

    data class State(
        val isAuthorized: Boolean = false,
        val isAuthChecked: Boolean = false,
        val user: String?=null,
		val isBottomBarVisible: Boolean = false,
        val selectedTab: AppTab = AppTab.Catalog
	)

    sealed interface Label {
        data class ClickTab(val bottomNavItem: AppTab) : Label
    }
}

class RootStoreFactory(
    private val storeFactory: StoreFactory
) {

    fun create(): RootStore =
        object : RootStore, Store<RootStore.Intent, RootStore.State, RootStore.Label> by storeFactory.create(
            name = "RootStore",
            initialState = RootStore.State(),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data class CheckAuth(val token: String?=null) : Action
    }

    private sealed interface Msg {
        data class TabChanged(val bottomNavItem: AppTab) : Msg
        data class Authorized(val user: String) : Msg
        data object AuthChecked : Msg
    }

    private class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                val token = "dsgsg" //authRep.isAuth()
                dispatch(Action.CheckAuth(token))
            }
        }
    }

    private class ExecutorImpl : CoroutineExecutor<RootStore.Intent, Action, RootStore.State, Msg, RootStore.Label>() {
        override fun executeIntent(intent: RootStore.Intent) {
            when(intent){


	            is RootStore.Intent.OnClickTab -> {
                    dispatch(Msg.TabChanged(intent.bottomNavItem))
                    publish(RootStore.Label.ClickTab(intent.bottomNavItem))
                }
            }
        }

        override fun executeAction(action: Action) {
            when (action) {
                is Action.CheckAuth -> {
                    if (action.token != null) {
                        dispatch(Msg.Authorized(("Beknur")))
                    }else{
                        dispatch(Msg.AuthChecked)
                    }
                }
            }
        }
    }

    private object ReducerImpl : Reducer<RootStore.State, Msg> {
        override fun RootStore.State.reduce(msg: Msg): RootStore.State {
            return when(msg){
                is Msg.TabChanged -> {
                    copy(selectedTab = msg.bottomNavItem)
                }

                is Msg.Authorized -> {
                    copy(isAuthorized = true,  isAuthChecked = true, user = msg.user,isBottomBarVisible=true)
                }

	            Msg.AuthChecked -> {
                    copy(isAuthorized = false,isAuthChecked = true)

                }
            }
        }
    }
}
