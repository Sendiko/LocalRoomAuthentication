package com.github.sendiko.localroomauthentication.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.github.sendiko.localroomauthentication.database.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert
    suspend fun saveUser(user: User)

    @Query("SELECT * FROM users")
    fun getUsers(): Flow<List<User>>

    /* Alternatif */
    @Query("SELECT * FROM users WHERE email =:email")
    fun getUserByEmail(email: String): User?
}