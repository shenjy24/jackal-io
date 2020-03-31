package com.jonas.netty.xml;

import com.jonas.netty.xml.pojo.Customer;
import com.jonas.netty.xml.pojo.Order;
import com.jonas.netty.xml.pojo.OrderFactory;
import org.jibx.runtime.*;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * TestOrder
 *
 * @author shenjy
 * @version 1.0
 * @date 2020-03-29
 */
public class TestOrder {
    private IBindingFactory factory;
    private StringWriter writer;
    private StringReader reader;
    private final static String CHARSET_NAME = "UTF-8";

    private String encode2Xml(Order order) throws JiBXException, IOException {
        factory = BindingDirectory.getFactory(Order.class);
        writer = new StringWriter();
        IMarshallingContext context = factory.createMarshallingContext();
        context.setIndent(2);
        context.marshalDocument(order, CHARSET_NAME, null, writer);
        String xml = writer.toString();
        writer.close();
        System.out.println(xml);
        return xml;
    }

    private Order decode2Order(String xml) throws JiBXException {
        factory = BindingDirectory.getFactory(Order.class);
        reader = new StringReader(xml);
        IUnmarshallingContext context = factory.createUnmarshallingContext();
        Order order = (Order) context.unmarshalDocument(reader);
        return order;
    }

    public static void main(String[] args) throws JiBXException, IOException {
        TestOrder instance = new TestOrder();
        Order order = OrderFactory.create(1);
        String xml = instance.encode2Xml(order);
        Order decodeOrder = instance.decode2Order(xml);
        System.out.println(decodeOrder);
    }


}
