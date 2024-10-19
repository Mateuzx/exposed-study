package org.example.resources.db.table

import org.jetbrains.exposed.sql.Table

object AirMax : Table() {
    val nikeId = integer("nike_id").references(Nike.nikeId)
    val creator = varchar("creator", 50)
}
