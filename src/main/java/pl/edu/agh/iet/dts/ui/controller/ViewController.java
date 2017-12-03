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

    @RequestMapping(value = "/components/login", method = GET)
    public String getLoginView() {
        return "forward:/";
    }

    @RequestMapping(value = "/components/user-info", method = GET)
    public String getUserInfoView() {
        return "forward:/";
    }

    @RequestMapping(value = "/components/unauthenticated", method = GET)
    public String getUnauthenticatedView() {
        return "forward:/";
    }

    @RequestMapping(value = "/components/map", method = GET)
    public String getMapView() {
        return "forward:/";
    }

    @RequestMapping(value = "/components/user-form", method = GET)
    public String getUserFormView() {
        return "forward:/";
    }

}
