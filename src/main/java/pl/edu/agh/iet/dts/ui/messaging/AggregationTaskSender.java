package pl.edu.agh.iet.dts.ui.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.edu.agh.iet.dts.ui.messaging.format.AggregationTask;

/**
 * @author Bartłomiej Grochal
 */
@Service
public class AggregationTaskSender {

    private final RabbitTemplate rabbitTemplate;
    private final String queueName;


    public AggregationTaskSender(final RabbitTemplate rabbitTemplate,
                                 @Value("${messaging.aggregationTaskSender.queueName}") final String queueName) {
        this.rabbitTemplate = rabbitTemplate;
        this.queueName = queueName;
    }


    public void sendTask(final AggregationTask aggregationTask) {
        rabbitTemplate.convertAndSend(queueName, aggregationTask);
    }

}
