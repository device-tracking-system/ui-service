package pl.edu.agh.iet.dts.ui.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import pl.edu.agh.iet.dts.ui.messaging.format.AggregationTask;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ScheduledFuture;

/**
 * @author Bartłomiej Grochal
 */
@Service
public class AggregationTaskScheduler {

    private final TaskScheduler threadPoolTaskScheduler;
    private final RabbitTemplate rabbitTemplate;
    private final String queueName;

    private final Map<String, ScheduledFuture> registeredSchedulers;


    public AggregationTaskScheduler(final TaskScheduler threadPoolTaskScheduler, final RabbitTemplate rabbitTemplate,
                                    @Value("${messaging.aggregationTaskSender.queueName}") final String queueName) {
        this.threadPoolTaskScheduler = threadPoolTaskScheduler;
        this.rabbitTemplate = rabbitTemplate;
        this.queueName = queueName;

        registeredSchedulers = new HashMap<>();
    }


    public synchronized void scheduleTask(final AggregationTask aggregationTask, final long aggregationTime) {
        final String id = aggregationTask.getId();
        final Optional<ScheduledFuture> currentScheduler = Optional.ofNullable(registeredSchedulers.get(id));
        currentScheduler.ifPresent(scheduler -> scheduler.cancel(false));

        ScheduledFuture newScheduler = threadPoolTaskScheduler.scheduleAtFixedRate(
                () -> rabbitTemplate.convertAndSend(queueName, aggregationTask), aggregationTime);
        registeredSchedulers.put(id, newScheduler);
    }

}
