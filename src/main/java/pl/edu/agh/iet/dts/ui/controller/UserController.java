package pl.edu.agh.iet.dts.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.iet.dts.ui.controller.json.PreferencesJSON;
import pl.edu.agh.iet.dts.ui.persistence.domain.Preferences;
import pl.edu.agh.iet.dts.ui.persistence.repository.PreferencesRepository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Bartłomiej Grochal
 */
@RestController
@RequestMapping(value = "/users/{userID}")
public class UserController {

    @Autowired PreferencesRepository preferencesRepository;


    @RequestMapping(value = "/preferences", method = GET)
    public Preferences getUserPreferences(@PathVariable("userID") String userID) {
        checkArgument(preferencesRepository.exists(userID));
        return preferencesRepository.findOne(userID);
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
    }

}
