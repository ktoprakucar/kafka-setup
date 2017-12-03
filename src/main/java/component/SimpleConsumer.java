package component;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.Properties;

public class SimpleConsumer {

    private final static String BOOTSTRAP_SERVERS = "localhost:9092,localhost:9093,localhost:9094";
    private Properties properties;
    private KafkaConsumer<Long, String> consumer;

    public SimpleConsumer() {
        setProperties();
        consumer = new KafkaConsumer<Long, String>(properties);
    }

    public void subscribeAndPrintTopics(String topicName, long timeout) {
        consumer.subscribe(Arrays.asList(topicName));
        ConsumerRecords<Long, String> records = consumer.poll(timeout);
        System.out.println(records.count());
        for (ConsumerRecord<Long, String> record : records) {
            System.out.printf("offset = %d, key = %s, value = %s\n",
                    record.offset(), record.key(), record.value());
        }
        consumer.commitAsync();
        consumer.close();
    }

    private void setProperties() {
        properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "KafkaConsumer");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public KafkaConsumer<Long, String> getConsumer() {
        return consumer;
    }

    public void setConsumer(KafkaConsumer<Long, String> consumer) {
        this.consumer = consumer;
    }
}
