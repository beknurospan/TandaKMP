package com.beknur.tanda.feature.auth


import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.beknur.tanda.feature.auth.AuthStore.Intent
import com.beknur.tanda.feature.auth.AuthStore.Label
import com.beknur.tanda.feature.auth.AuthStore.State

interface AuthStore : Store<Intent, State, Label> {

    sealed interface Intent {
	    data class OnLoginClick(val email: String, val password: String) : Intent
    }

    data class State(
		val data:String
	)

    sealed interface Label {
		data object LoginSuccess : Label
    }

}

class AuthStoreFactory(
    private val storeFactory: StoreFactory
) {

    fun create(): AuthStore =
        object : AuthStore, Store<Intent, State, Label> by storeFactory.create(
            name = "AuthStore",
            initialState = State(""),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
    }

    private sealed interface Msg {
    }

    private class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
        }
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent) {
			when(intent){
				is Intent.OnLoginClick->{
					//repo.login(intent.email,intent.password)
					//token.value=repo.token.value
					publish(Label.LoginSuccess)
				}
			}
        }

        override fun executeAction(action: Action) {
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State =
             State("")
    }
}
