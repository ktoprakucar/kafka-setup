package Main;

import component.SimpleConsumer;
import component.SimpleProducer;
import org.junit.Before;
import org.junit.Test;

public class ProducerConsumerDemo {

    private SimpleConsumer consumer;
    private SimpleProducer producer;

    @Before
    public void setUp() {
        producer = new SimpleProducer();
        consumer = new SimpleConsumer();
    }

    @Test
    public void should_producer_produces_topic_then_consumer_pull() {
        String topicName = "kafka-init";
        Long timeOut = 1000L;
        Long key = 1L;
        String value = "meydey meydey";
        producer.send(topicName, key, value, 25);
        consumer.subscribeAndPrintTopics(topicName, timeOut);
    }
}
