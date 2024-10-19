package org.example
import org.example.resources.db.table.AirMax
import org.example.resources.db.table.Nike
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SchemaUtils.create
import kotlin.random.Random
import org.jetbrains.exposed.sql.SchemaUtils.drop
import org.jetbrains.exposed.sql.transactions.TransactionManager
import java.sql.Connection

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    Database.connect("jdbc:h2:mem:testdb", driver = "org.h2.Driver")

    transaction {
        create(Nike,AirMax)

        val nikeNames = listOf("AirMax1", "AirMax90", "AirMax95", "AirMax97", "AirMax98")
        for (i in 1..10) {
            Nike.insert {
                it[nameShoe] = nikeNames[Random.nextInt(nikeNames.size)]
                it[yearRelease] = Random.nextInt(1990, 2022) // Anos entre 1990 e 2021
            }
        }


        val creators = listOf("John", "Jane", "Alice", "Bob", "Charlie")

        for (i in 1..10) {
            AirMax.insert {
                it[nikeId] = i
                it[creator] = creators[Random.nextInt(creators.size)] // Atribui um criador aleatório
            }
        }

        val complexJoin = Join(
            Nike, AirMax,
            onColumn = Nike.nikeId, otherColumn = AirMax.nikeId,
            joinType = JoinType.INNER,
            additionalConstraint = { Nike.nikeId eq AirMax.nikeId }).selectAll()



        println(complexJoin.map { it[AirMax.creator] })


//        println(AirMax.selectAll().map { it[AirMax.creator] })

    }

}