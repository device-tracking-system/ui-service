package pl.edu.agh.iet.dts.ui.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
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


    @RabbitListener(bindings = @QueueBinding(
                        value = @Queue(value = "${messaging.aggregatedDataReceiver.queueName}", durable = "true", exclusive = "false", autoDelete = "false"),
                        exchange = @Exchange(value = "${messaging.aggregatedDataReceiver.exchangeName}", type = "direct", durable = "true", autoDelete = "false"),
                        key = "messaging.aggregatedDataReceiver.bindingName"))
    public void receiveMessage(final Message message) throws IOException {
        final AggregatedData aggregatedData = new ObjectMapper().readValue(new String((byte[]) message.getPayload()), AggregatedData.class);
        final AggregatedPositions aggregatedPositions =
                new AggregatedPositions(aggregatedData.getOwnerId(), aggregatedData.getData(), aggregatedData.getTimestamp());
        aggregatedPositionsRepository.save(aggregatedPositions);
    }

}
