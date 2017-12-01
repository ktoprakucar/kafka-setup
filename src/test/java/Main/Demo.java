package Main;

import component.SimpleConsumer;
import component.SimpleProducer;
import org.junit.Before;
import org.junit.Test;

public class Demo {
    SimpleProducer producer;
    SimpleConsumer consumer;

    @Before
    public void setUp() {
        producer = new SimpleProducer();
        consumer = new SimpleConsumer();
    }

    @Test
    public void should_producer_send_a_message() {
        String topicName = "john doe";
        Integer key = 7;
        Integer value = 10;
        producer.send(topicName, key, value);
        consumer.subscribeAndPrintTopics(topicName, 100000L);
    }
}
