import com.alibaba.fastjson.JSON;
import com.hpanushan.model.Student;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class FlinkMySQLProducer {

    public static final String broker_list = "node2:9092,node3:9092,node4:9092";
    public static final String topic = "student1";  //kafka topic needs to use the same topic as flink program

    public static void writeToKafka() throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", broker_list);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer producer = new KafkaProducer<String, String>(props);

        for (int i = 1; i <= 100; i++) {
            Student student = new Student(i, "anushan" + i, "password" + i, 18 + i);
            ProducerRecord record = new ProducerRecord<String, String>(topic, null, null, JSON.toJSONString(student));
            producer.send(record);
            System.out.println("send data: " + JSON.toJSONString(student));
        }
        producer.flush();
    }

    public static void main(String[] args) throws InterruptedException {
        writeToKafka();
    }

}
