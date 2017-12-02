package pl.edu.agh.iet.dts.ui.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.iet.dts.ui.controller.json.UserJSON;

import java.util.logging.Logger;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * @author Bartłomiej Grochal
 */
@RestController
public class UserController {

    @RequestMapping(value = "/users", method = POST)
    public void postUser(@RequestBody UserJSON userJSON) {
        Logger.getLogger(UserController.class.getName())
                .info("POST /users request received with data:\n" + userJSON);
    }

}
