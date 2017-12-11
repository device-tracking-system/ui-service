package pl.edu.agh.iet.dts.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.iet.dts.ui.controller.json.UserJSON;
import pl.edu.agh.iet.dts.ui.persistence.domain.Preferences;
import pl.edu.agh.iet.dts.ui.persistence.repository.PreferencesRepository;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Bartłomiej Grochal
 */
@RestController
@RequestMapping(value = "/users/{userID}")
public class UserController {

    @Autowired PreferencesRepository preferencesRepository;


    @RequestMapping(value = "/preferences", method = POST)
    public void postUser(@PathVariable("userID") String userID, @RequestBody UserJSON userJSON) {
        checkArgument(0 < userJSON.points && 10 >= userJSON.points);
        checkArgument(0 < userJSON.period && 60 >= userJSON.period);
        checkArgument(0 < userJSON.aggregationTime);
        checkNotNull(userID);

        Preferences preferences = new Preferences(userID, userJSON.points, userJSON.period, userJSON.aggregationTime);
        preferencesRepository.save(preferences);
    }

}
