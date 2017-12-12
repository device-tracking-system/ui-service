package pl.edu.agh.iet.dts.ui.messaging.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author Bartłomiej Grochal
 */
@Configuration
public class AggregationTaskSchedulerConfiguration {

    @Bean
    public TaskScheduler ThreadPoolTaskScheduler(@Value("${scheduler.threads.poolSize}") final int threadsNumber) {
        final ThreadPoolTaskScheduler poolScheduler = new ThreadPoolTaskScheduler();
        poolScheduler.setThreadNamePrefix("AggregationTaskSchedulerPool");
        poolScheduler.setPoolSize(threadsNumber);

        return poolScheduler;
    }

}
