package com.example.realmtest.androidApp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.realmtest.shared.Greeting
import android.widget.TextView
import com.example.realmtest.shared.Database

fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()

        for (i in 1..10) {
            Database.addPerson("Foo$i", 30 + i)
        }
//        val person = Database.queryPerson("Foo9")
//        println("### Person: $person")
//
//        val persons = Database.allPerson()
//        println("### Persons: $persons")
    }
}
