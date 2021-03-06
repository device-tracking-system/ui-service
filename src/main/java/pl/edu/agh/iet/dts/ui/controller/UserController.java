package pl.edu.agh.iet.dts.ui.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.iet.dts.ui.controller.json.PreferencesJSON;
import pl.edu.agh.iet.dts.ui.messaging.AggregationTaskScheduler;
import pl.edu.agh.iet.dts.ui.messaging.format.AggregationTask;
import pl.edu.agh.iet.dts.ui.persistence.domain.AggregatedPositions;
import pl.edu.agh.iet.dts.ui.persistence.domain.Preferences;
import pl.edu.agh.iet.dts.ui.persistence.repository.AggregatedPositionsRepository;
import pl.edu.agh.iet.dts.ui.persistence.repository.PreferencesRepository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.springframework.http.HttpStatus.NOT_ACCEPTABLE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Bartłomiej Grochal
 */
@RestController
@RequestMapping(value = "/users/{userID}")
public class UserController {

    @Autowired private PreferencesRepository preferencesRepository;
    @Autowired private AggregatedPositionsRepository aggregatedPositionsRepository;

    @Autowired private AggregationTaskScheduler aggregationTaskScheduler;


    @RequestMapping(value = "/preferences", method = GET)
    public Preferences getUserPreferences(@PathVariable("userID") String userID) {
        checkArgument(preferencesRepository.exists(userID));

        final Preferences preferences = preferencesRepository.findOne(userID);

        LoggerFactory.getLogger(UserController.class)
                .debug(String.format("[GET /users/%s/preferences] %s", userID, preferences));
        return preferences;
    }

    @RequestMapping(value = "/preferences", method = POST)
    public void postUserPreferences(@PathVariable("userID") String userID, @RequestBody PreferencesJSON preferencesJSON) {
        checkArgument(0 < preferencesJSON.points && 10 >= preferencesJSON.points);
        checkArgument(0 < preferencesJSON.period && 60 >= preferencesJSON.period);
        checkArgument(0 < preferencesJSON.aggregationTime);
        checkNotNull(userID);

        final Preferences preferences =
                new Preferences(userID, preferencesJSON.points, preferencesJSON.period, preferencesJSON.aggregationTime);
        preferencesRepository.save(preferences);

        LoggerFactory.getLogger(UserController.class)
                .debug(String.format("POST /users/%s/preferences] %s", userID, preferences));
        scheduleAggregationTask(preferences.getId(), preferences.getPoints(), preferences.getPeriod(), preferences.getAggregationTime());
    }

    @RequestMapping(value = "/positions", method = GET)
    public AggregatedPositions getUserPositions(@PathVariable("userID") String userID) {
        checkArgument(aggregatedPositionsRepository.exists(userID));

        final AggregatedPositions aggregatedPositions = aggregatedPositionsRepository.findOne(userID);

        LoggerFactory.getLogger(UserController.class)
                .debug(String.format("GET /users/%s/positions] %s", userID, aggregatedPositions));
        return aggregatedPositions;
    }

    @ResponseStatus(code = NOT_ACCEPTABLE, reason = "Improper request argument")
    @ExceptionHandler(value = {IllegalArgumentException.class, NullPointerException.class})
    public void handlePreconditionsException() {
    }


    private void scheduleAggregationTask(final String id, final int points, final int period, int aggregationTime) {
        final long timestamp = aggregatedPositionsRepository.exists(id) ?
                aggregatedPositionsRepository.findOne(id).getTimestamp() : 0L;
        final AggregationTask aggregationTask = new AggregationTask(id, points, period, timestamp);
        aggregationTaskScheduler.scheduleTask(aggregationTask, ((long) aggregationTime) * 60 * 1000);
    }

}
