package Main;

import component.SimpleConsumer;
import component.SimpleProducer;
import org.junit.Before;
import org.junit.Test;

public class ProducerConsumerDemo {

    @Test
    public void should_producer_produces_topic_then_consumer_pull() {
        //given
        SimpleConsumer consumer = new SimpleConsumer();
        SimpleProducer producer = new SimpleProducer("kafkaProcuder");
        String topicName = "kafka-init";
        Long timeOut = 1000L;
        Long key = 1L;
        String value = "meydey meydey";

        //when
        producer.send(topicName, key, value, 25);
        consumer.subscribeAndPrintTopics(topicName, timeOut);
    }

    @Test
    public void should_three_producers_produce_topics_then_consumer_pull_the_selected_topic() {
        //given
        SimpleConsumer consumer = new SimpleConsumer();
        SimpleProducer firstProducer = new SimpleProducer("payment");
        SimpleProducer secondProducer = new SimpleProducer("refund");
        SimpleProducer thirdProducer = new SimpleProducer("settlement");

        Long timeOut = 1000L;
        Long key = 1L;
        String firstMessage = "{\"price\": 300, \"type\": \"payment\"}";
        String secondMessage = "{\"price\": 150, \"type\": \"refund\"}";
        String thirdMessage = "{\"price\": 140, \"type\": \"settlement\"}";

        //when
        firstProducer.send("balance", key, firstMessage, 1);
        secondProducer.send("balance", key, secondMessage, 1);
        thirdProducer.send("balance", key, thirdMessage, 1);
        consumer.subscribeAndPrintTopics("balance", timeOut);
    }
}
