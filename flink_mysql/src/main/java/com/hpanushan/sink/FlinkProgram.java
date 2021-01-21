package com.hpanushan.sink;

import com.alibaba.fastjson.JSON;
import com.hpanushan.model.Student;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

public class FlinkProgram {

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // Setting properties
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "node2:9092,node3:9092,node4:9092");
        properties.setProperty("group.id", "test");

        SingleOutputStreamOperator<Student> student = env
                .addSource(new FlinkKafkaConsumer<>("student1", new SimpleStringSchema(), properties))
                .setParallelism(1)
                .map(string -> JSON.parseObject(string, Student.class)); //Fastjson parses strings into student objects

        student.addSink(new SinkToMySQL()).name("MySQL"); //Data sink to mysql

        env.execute("Flink add sink");
    }
}
