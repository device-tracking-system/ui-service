package pl.edu.agh.iet.dts.ui.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Bartłomiej Grochal
 */
@Configuration
public class AggregationTaskSenderConfiguration {

    @Bean
    public Queue AggregationTaskQueue(@Value("${messaging.aggregationTaskSender.queueName}") final String queueName) {
        return new Queue(queueName, false);
    }

    @Bean
    public TopicExchange AggregationTaskTopicExchange(@Value("${messaging.aggregationTaskSender.exchangeName}") String exchangeName) {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding AggregationTaskTopicExchangeToQueueBinding(@Qualifier("AggregationTaskQueue") Queue queue,
                                                              @Qualifier("AggregationTaskTopicExchange") TopicExchange topicExchange) {
        return BindingBuilder
                .bind(queue)
                .to(topicExchange)
                .with(queue.getName());
    }

}
