package com.github.sendiko.localroomauthentication.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.sendiko.localroomauthentication.database.UserDao
import com.github.sendiko.localroomauthentication.database.entities.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class LoginViewModel(private val dao: UserDao) : ViewModel() {

    /* Mengambil semua data user */
    val users = dao.getUsers()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            emptyList()
        )

    val pesan = MutableStateFlow("")

    init {
        if (users.value.isEmpty()) {
            viewModelScope.launch {
                repeat(10) {
                    val user = User(
                        username = "User $it",
                        email = "emailUser$it@blabla.com",
                        password = "user${it}password"
                    )
                }
            }
        }
    }

    /* Cara manual(tidak disarankan jika data banyak) */
    fun login(email: String, password: String) {
        /* mencari list user dengan email masukan */
        val user = users.value.find { it.email == email }
        if (user != null) {
            val match = user.password == password
            /* Tandai berhasil login */
            pesan.value = "Berhasil Login"
        }
    }

    /* Menggunakan query */
    fun loginWithQuery(email: String, password: String) {
        val user = dao.getUserByEmail(email)
        if (user != null) {
            val match = user.password == password
            /* Tandai berhasil login */
            pesan.value = "Berhasil Login"
        }
    }
}