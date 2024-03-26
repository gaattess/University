package com.example.quizapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.quizapp.daos.AnswerFillDao
import com.example.quizapp.daos.AnswerMultipleDao
import com.example.quizapp.daos.QuestionDao
import com.example.quizapp.daos.UserDao
import com.example.quizapp.daos.UserQuizProgressDao
import com.example.quizapp.entities.AnswerFillEntity
import com.example.quizapp.entities.AnswerMultipleEntity
import com.example.quizapp.entities.QuestionEntity
import com.example.quizapp.entities.UserEntity
import com.example.quizapp.entities.UserQuizProgressEntity

@Database(
    entities =
        [
            UserEntity::class,
            UserQuizProgressEntity::class,
            QuestionEntity::class,
            AnswerMultipleEntity::class,
            AnswerFillEntity::class
        ],
    version = 9
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    abstract fun userQuizProgressDao(): UserQuizProgressDao

    abstract fun questionDao(): QuestionDao

    abstract fun answerMultipleDao(): AnswerMultipleDao

    abstract fun answerFillDao(): AnswerFillDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.

        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            INSTANCE
                ?: synchronized(this) {
                    val instance =
                        Room.databaseBuilder(context, AppDatabase::class.java, "sample_db.db")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .createFromAsset("sample_db.db")
                            .build()

                    INSTANCE = instance
                    instance
                }
    }
}
