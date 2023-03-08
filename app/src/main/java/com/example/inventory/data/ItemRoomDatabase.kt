package com.example.inventory.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//Pour que Room puisse créer la base de données, l'annotation @Database doit comporter plusieurs arguments.
//Spécifiez Item en tant que seule classe contenant la liste d'entities (entités).
//Définissez version sur 1. Chaque fois que vous modifiez le schéma de la table de base de données, vous devez incrémenter le numéro de version.
//Définissez exportSchema sur false pour ne pas conserver les sauvegardes de l'historique des versions de schéma.
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class ItemRoomDatabase : RoomDatabase() {
    //La base de données doit connaître le DAO. Dans le corps de la classe, déclarez une fonction abstraite qui renvoie ItemDao
    abstract fun itemDao(): ItemDao
    //L'objet compagnon permet d'accéder aux méthodes de création ou d'obtention de la base de données en utilisant le nom de classe comme qualificatif.
    companion object {
        @Volatile //Cela permet de s'assurer que la valeur de la variable INSTANCE est toujours à jour et identique pour tous les threads d'exécution.
        private var INSTANCE: ItemRoomDatabase? = null //La variable INSTANCE conservera une référence à la base de données, si vous en avez créé une.
        fun getDatabase(context: Context): ItemRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext,
                                                    ItemRoomDatabase::class.java,
                                              "item_database")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}