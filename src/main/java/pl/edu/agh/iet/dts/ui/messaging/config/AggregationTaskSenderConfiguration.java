package pl.edu.agh.iet.dts.ui.messaging.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
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
        return new Queue(queueName, true, false, false);
    }

    @Bean
    public DirectExchange AggregationTaskDirectExchange(@Value("${messaging.aggregationTaskSender.exchangeName}") final String exchangeName) {
        return new DirectExchange(exchangeName, true, false);
    }

    @Bean
    public Binding AggregationTaskDirectExchangeToQueueBinding(@Qualifier("AggregationTaskQueue") final Queue queue,
                                                               @Qualifier("AggregationTaskDirectExchange") final DirectExchange exchange,
                                                               @Value("${messaging.aggregationTaskSender.bindingName}") final String bindingName) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(bindingName);
    }

}
