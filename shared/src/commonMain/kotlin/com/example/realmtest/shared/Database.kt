package com.example.realmtest.shared

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import io.realm.runtimeapi.RealmModel
import io.realm.runtimeapi.RealmModule
import io.realm.runtimeapi.RealmObject


@RealmObject
class Person: RealmModel {
    var name: String = "Foo"
    var age: Int = 42
}

@RealmModule
class Entities

object Database {
    val realm: Realm by lazy {
        val configuration = RealmConfiguration.Builder()
            .schema(Entities())
            .build()

        Realm.open(configuration)
    }

    fun addPerson(name: String, age: Int): Person {
        realm.beginTransaction()

        val person: Person = realm.create(Person::class).apply {
            this.name = name
            this.age = age
        }
        realm.commitTransaction()
        return person
    }

    fun allPerson(): RealmResults<Person> {
        return realm.objects(Person::class)
    }

    fun queryPerson(name: String): RealmResults<Person> {
        return realm.objects(Person::class).query("name = $0", name)
    }

    fun deleteAllPerson() {
        realm.beginTransaction()
        realm.objects(Person::class).delete()
        realm.commitTransaction()
    }
}