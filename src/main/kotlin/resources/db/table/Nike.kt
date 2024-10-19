package org.example.resources.db.table

import org.jetbrains.exposed.sql.Table

object Nike : Table() {
    val nikeId = integer("nike_id").autoIncrement()
    val nameShoe = varchar("name_shoe",30)
    val yearRelease = integer("year_release")

    override val primaryKey = PrimaryKey(nikeId, name = "nike_id")
}


