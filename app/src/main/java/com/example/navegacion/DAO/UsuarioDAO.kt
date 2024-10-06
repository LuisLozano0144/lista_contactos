// UserDAO.kt
package com.example.projecj_bd.DAO

import androidx.room.*
import com.example.projecj_bd.Model.User

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user : User)

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Delete
    suspend fun delete(user: User)
}
