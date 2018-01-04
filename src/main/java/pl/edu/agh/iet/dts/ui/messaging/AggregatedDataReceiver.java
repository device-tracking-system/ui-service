package pl.edu.agh.iet.dts.ui.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import pl.edu.agh.iet.dts.ui.messaging.format.AggregatedData;
import pl.edu.agh.iet.dts.ui.persistence.domain.AggregatedPositions;
import pl.edu.agh.iet.dts.ui.persistence.repository.AggregatedPositionsRepository;

import java.io.IOException;

/**
 * @author Bartłomiej Grochal
 */
@Service
public class AggregatedDataReceiver {

    @Autowired private AggregatedPositionsRepository aggregatedPositionsRepository;

    @Value("${messaging.aggregatedDataReceiver.exchangeName}") private String exchangeName;
    @Value("${messaging.aggregatedDataReceiver.bindingName}") private String bindingName;


    @RabbitListener(bindings = @QueueBinding(
                        value = @Queue(value = "${messaging.aggregatedDataReceiver.queueName}", durable = "true", exclusive = "false", autoDelete = "false"),
                        exchange = @Exchange(value = "${messaging.aggregatedDataReceiver.exchangeName}", type = "direct", durable = "true", autoDelete = "false"),
                        key = "${messaging.aggregatedDataReceiver.bindingName}"))
    public void receiveMessage(final Message message) throws IOException {
        final AggregatedData aggregatedData = new ObjectMapper().readValue(new String((byte[]) message.getPayload()), AggregatedData.class);
        final AggregatedPositions aggregatedPositions =
                new AggregatedPositions(aggregatedData.getOwnerId(), aggregatedData.getData(), aggregatedData.getTimestamp());
        aggregatedPositionsRepository.save(aggregatedPositions);

        LoggerFactory.getLogger(AggregatedDataReceiver.class)
                .debug(String.format("[MSG %s/%s] %s", exchangeName, bindingName, aggregatedPositions));
    }

}
