package io.msgr.agg;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.ConnectionFactory;

@SpringBootApplication
@EnableJms
public class App {

	public static final String TOPIC = "request-add";

	public String url = "tcp://10.0.3.10:61616";

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

    // from activemq introduction
    @Bean
    public ConnectionFactory connectionFactory(){
        System.out.println(" ---------------- calling connectionFactory ------------------ ");
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(url);
        return connectionFactory;
    }


    // from spring tutorial
    @Bean
    public JmsListenerContainerFactory<?> myFactory(/*ConnectionFactory connectionFactory,*/
                                                    DefaultJmsListenerContainerFactoryConfigurer configurer) {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(url);
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        // This provides all boot's default to this factory, including the message converter
        configurer.configure(factory, connectionFactory);
        // You could still override some of Boot's default if necessary.
        return factory;
    }

}
