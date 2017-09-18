package example.hello;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping(produces = "text/html")
public class HelloMVCController {
    public static final String HELLO_VIEW_URL = "/";

    @RequestMapping(value = HELLO_VIEW_URL, method = RequestMethod.GET)
    public String greeting(Model model, @RequestHeader(name = "User-Agent") String userAgent) {
        UserAgent parsed = UserAgent.parseUserAgentString(userAgent);
        model.addAttribute("subject", (parsed.getBrowser() != Browser.UNKNOWN ? parsed.getBrowser().getName(): userAgent));
        return "greeting";
    }
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin() {
        return "admin";
    }
    @RequestMapping(value = "/login/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }
}