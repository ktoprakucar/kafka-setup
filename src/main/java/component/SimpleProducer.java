package component;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.LongSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class SimpleProducer {

    private final static String BOOTSTRAP_SERVERS = "localhost:9092,localhost:9093,localhost:9094";
    private Properties properties;
    private Producer<Long, String> producer;

    public SimpleProducer() {
        setProperties();
        producer = new KafkaProducer<Long, String>(properties);
    }

    public void send(String topicName, Long key, String value, int iterationCount) {
        try {
            for (int i = 0; i < iterationCount; i++) {
                ProducerRecord<Long, String> record = new
                        ProducerRecord<Long, String>
                        (topicName, key, value + ".v" + i);
                producer.send(record);

            }
        } finally {
            producer.flush();
            producer.close();
        }
    }

    public void setProperties() {
        properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "KafkaProducer");
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }
}
