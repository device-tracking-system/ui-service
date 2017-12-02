package pl.edu.agh.iet.dts.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Bartłomiej Grochal
 */
@Controller
public class RedirectController {

    @RequestMapping(value = "", method = GET)
    public void redirectEmptyEndpoint(HttpServletResponse response) throws IOException {
        response.sendRedirect("/app");
    }

}
