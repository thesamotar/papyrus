/*
package com.example.papyrus.data

import androidx.room.*
import com.example.papyrus.data.entities.UserProfileEntity
import com.example.papyrus.data.entities.MedalEntity

@Dao
interface ProfileDao {
    @Query("SELECT * FROM user_profile WHERE id = 'main' LIMIT 1")
    suspend fun getProfile(): UserProfileEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertProfile(profile: UserProfileEntity)

    @Query("SELECT * FROM medals")
    suspend fun getMedals(): List<MedalEntity>
}*/
