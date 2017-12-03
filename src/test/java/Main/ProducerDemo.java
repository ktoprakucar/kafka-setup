package Main;

import component.SimpleProducer;
import org.junit.Before;
import org.junit.Test;

public class ProducerDemo {
    SimpleProducer producer;

    @Before
    public void setUp() {
        producer = new SimpleProducer("kafkaProducer");
    }

    @Test
    public void should_producer_send_a_message() {
        String topicName = "kafka-init";
        Long key = 1L;
        String value = "meydey meydey";
        producer.send(topicName, key, value, 25);
    }
}
