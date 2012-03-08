package com.acme.order;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {
    public ObjectFactory() {
    }

    public PurchaseOrder createPurchaseOrder() {
        return new PurchaseOrder();
    }
}
