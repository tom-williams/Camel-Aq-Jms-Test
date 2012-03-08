import org.apache.camel.builder.RouteBuilder
import org.apache.camel.spi.DataFormat
import org.apache.camel.converter.jaxb.JaxbDataFormat

class AqMessageRoute extends RouteBuilder {
    def grailsApplication

    @Override
    void configure() {
        def config = grailsApplication?.config

        DataFormat jaxb = new JaxbDataFormat("com.acme.order")

        from('oracleQueue:queue:CAMEL_TEST?jmsMessageType=Text').transacted('propagationRequired').unmarshal(jaxb).to('bean:persistAqMessageService?method=persistAqMessage')
    }
}
