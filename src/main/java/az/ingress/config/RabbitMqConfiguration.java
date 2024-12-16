package az.ingress.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {
    private final String ratingQ;
    private final String ratingDLQ;
    private final String ratingQExchange;
    private final String ratingDLQExchange;
    private final String ratingQKey;
    private final String ratingDLQKey;

    public RabbitMqConfiguration(@Value("${rabbitmq.publisher-service.queue}") String ratingQ,
                                 @Value("${rabbitmq.publisher-service.dlq}") String ratingDLQ) {

        this.ratingQ = ratingQ;
        this.ratingDLQ = ratingDLQ;
        this.ratingQExchange = ratingQ + "_EXCHANGE";
        this.ratingDLQExchange = ratingDLQ + "_EXCHANGE";
        this.ratingQKey = ratingQ + "_KEY";
        this.ratingDLQKey = ratingDLQ + "_KEY";
    }

    @Bean
    DirectExchange publisherDLQExchange() {
        return new DirectExchange(ratingDLQExchange);
    }

    @Bean
    DirectExchange publisherQExchange() {
        return new DirectExchange(ratingQExchange);
    }

    @Bean
    Queue publisherDLQ() {
        return QueueBuilder.durable(ratingDLQ).build();
    }

    @Bean
    Queue publisherQ() {
        return QueueBuilder.durable(ratingQ)
                .withArgument("x-dead-letter-exchange", ratingDLQExchange)
                .withArgument("x-dead-letter-routing-key", ratingDLQKey)
                .build();
    }

    @Bean
    Binding publisherDLQBinding() {
        return BindingBuilder.bind(publisherDLQ())
                .to(publisherDLQExchange()).with(ratingDLQKey);
    }

    @Bean
    Binding publisherQBinding() {
        return BindingBuilder.bind(publisherQ())
                .to(publisherQExchange()).with(ratingQKey);
    }
}