package Main;

import component.SimpleConsumer;
import org.junit.Before;
import org.junit.Test;

public class ConsumerDemo {
    SimpleConsumer consumer;

    @Before
    public void setUp() {
        consumer = new SimpleConsumer();
    }

    @Test
    public void should_retrieve_topic() {
        String topicName = "kafka-init";
        Long timeOut = 1000L;
        consumer.subscribeAndPrintTopics(topicName, timeOut);
    }
}
