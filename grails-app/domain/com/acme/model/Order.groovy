package com.acme.model

class Order {
    String name
    Double price
    Integer amount


    static mapping = {
        table 'NEW_DOMAIN_ORDERS_TST'
    }

    static constraints = {
        amount(range: 1..10)
        name(blank: false)
    }

    public String toString() {
        return "id: ${id},\nname: ${name},\nprice ${price},\namount ${amount}\n"
    }
}
