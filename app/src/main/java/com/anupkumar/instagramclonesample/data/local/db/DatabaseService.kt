package com.anupkumar.instagramclonesample.data.local.db

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.anupkumar.instagramclonesample.data.local.db.entity.DummyEntity


@Database(
    entities = [DummyEntity::class],
    exportSchema = false,
    version = 1
)
abstract class DatabaseService : RoomDatabase() {

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun clearAllTables() {
        TODO("Not yet implemented")
    }

}