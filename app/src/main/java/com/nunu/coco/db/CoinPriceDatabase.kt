package com.nunu.coco.db

import android.content.Context
import androidx.room.*
import com.nunu.coco.db.dao.InterestCoinDao
import com.nunu.coco.db.dao.SelectedCoinPriceDao
import com.nunu.coco.db.entity.DateConverters
import com.nunu.coco.db.entity.InterestCoinEntity
import com.nunu.coco.db.entity.SelectedCoinPriceEntity

@Database(entities = [InterestCoinEntity::class, SelectedCoinPriceEntity::class], version = 2)
@TypeConverters(DateConverters::class)
abstract class CoinPriceDatabase : RoomDatabase() {

    abstract fun interestCoinDao() : InterestCoinDao
    abstract fun selectedCoinDao() : SelectedCoinPriceDao

    companion object{

        @Volatile
        private var INSTANCE : CoinPriceDatabase? = null

        fun getDatabase(
            context : Context
        ) : CoinPriceDatabase{

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoinPriceDatabase::class.java,
                    "coin_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}