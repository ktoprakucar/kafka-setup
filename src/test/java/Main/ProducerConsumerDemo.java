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
        SimpleProducer firstProducer = new SimpleProducer("firstProducer");
        SimpleProducer secondProducer = new SimpleProducer("secondProducer");
        SimpleProducer thirdProducer = new SimpleProducer("thirdProducer");

        Long timeOut = 1000L;
        Long key = 1L;
        String value = "meydey meydey";

        //when
        firstProducer.send("kafka-init", key, value, 25);
        secondProducer.send("kafka-process", key, value, 25);
        thirdProducer.send("kafka-done", key, value, 25);
        consumer.subscribeAndPrintTopics("kafka-done", timeOut);
    }
}
