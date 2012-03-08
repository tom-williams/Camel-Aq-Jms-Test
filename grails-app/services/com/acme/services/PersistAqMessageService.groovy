package com.acme.services

import com.acme.order.PurchaseOrder
import com.acme.model.Order
import org.springframework.validation.FieldError
import com.acme.mongo.OrderMash

class PersistAqMessageService {
    static transactional = true

    def persistAqMessage(PurchaseOrder purchaseOrder) {
        log.info "persistAqMessage called:"
        def myOrder = new Order(name: purchaseOrder.name, price: purchaseOrder.price, amount: purchaseOrder.amount)
        log.info "Purchase order de-serialized and into Order object: ${myOrder}"
        if (!myOrder.save(flush: true)) {
            log.error "Error occured while saving Order object:"
            def errors = myOrder.errors
            errors.fieldErrors.each {FieldError fieldError ->
                log.error "Error in field: ${fieldError.getField()}"
                log.error "Error code: ${fieldError.code}"
                log.error "Rejected value: ${fieldError.rejectedValue.toString()}"
            }
            throw new RuntimeException("Yada-yada")

        } else {
            log.info "Order object saved!"
        }


        def orderMash
        if (myOrder.name.equals("FAILME")) {
            orderMash = new OrderMash(name: myOrder.name, price: myOrder.price, amount: myOrder.amount)
        } else {
            orderMash = new OrderMash(pieceId: myOrder.id, name: myOrder.name, price: myOrder.price, amount: myOrder.amount)
        }
        if (!orderMash.save(flush: true)) {
            log.error "Error occured while saving MashOrder object:"
            def errors = myOrder.errors
            errors.fieldErrors.each {FieldError fieldError ->
                log.error "Error in field: ${fieldError.getField()}"
                log.error "Error code: ${fieldError.code}"
                log.error "Rejected value: ${fieldError.rejectedValue.toString()}"
            }
            throw new RuntimeException("Roll-me back back")
        } else {
            log.info "OrderMash object persisted to Mongo"
        }
    }

    def putMessageOnQueue(Order newOrder) {
        sendMessage('oracleQueue:queue:CAMEL_TEST?jmsMessageType=Text', "<purchaseOrder name=\"${newOrder.name}\" price=\"${newOrder.price}\" amount=\"${newOrder.amount}\" />")
    }
}
