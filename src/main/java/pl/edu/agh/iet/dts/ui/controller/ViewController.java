package pl.edu.agh.iet.dts.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Bartłomiej Grochal
 */
@Controller
@RequestMapping(value = "/app")
public class ViewController {

    @RequestMapping(value = "", method = GET)
    public String getHomeView() {
        return "forward:/";
    }

}
