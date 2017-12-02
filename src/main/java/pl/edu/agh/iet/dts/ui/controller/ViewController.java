package pl.edu.agh.iet.dts.ui.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Bartłomiej Grochal
 */
@RestController
@RequestMapping(value = "app")
public class ViewController {

    @RequestMapping(value = "", method = GET)
    public String getHomeView() {
        return "This is the home view.";
    }

}
