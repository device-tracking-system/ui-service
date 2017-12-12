package pl.edu.agh.iet.dts.ui.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.iet.dts.ui.messaging.format.AggregatedData;
import pl.edu.agh.iet.dts.ui.persistence.domain.AggregatedPositions;
import pl.edu.agh.iet.dts.ui.persistence.repository.AggregatedPositionsRepository;

/**
 * @author Bartłomiej Grochal
 */
@Service
public class AggregatedDataReceiver {

    @Autowired private AggregatedPositionsRepository aggregatedPositionsRepository;


    @RabbitListener(queues = "${messaging.aggregatedDataReceiver.queueName}")
    public void receiveMessage(AggregatedData aggregatedData) {
        final AggregatedPositions aggregatedPositions =
                new AggregatedPositions(aggregatedData.getId(), aggregatedData.getData(), aggregatedData.getTimestamp());
        aggregatedPositionsRepository.save(aggregatedPositions);
    }

}
