package com.acme.mongo

import org.bson.types.ObjectId
import com.mongodb.WriteConcern

class OrderMash {
    ObjectId id

    Long pieceId

    String name
    Double price
    Integer amount

    static mapWith = "mongo"

    static mapping = {
        pieceId index: true, indexAttributes: [unique: true, dropDups: true]
        writeConcern WriteConcern.SAFE
    }

    static constraints = {
        amount(blank: true)
        name(blank: true)
        price(blank: true)
        pieceId(blank: false)
    }
}
