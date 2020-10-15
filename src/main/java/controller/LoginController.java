package controller;

import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String loginForm(Model model, @CookieValue(value = "setUser", defaultValue = "") String setUser){
        model.addAttribute("setUser",setUser);
        User user = new User();
        model.addAttribute("user",user);
        return "login";
    }

    @PostMapping("/save")
    public String saveLogin(User user, HttpServletResponse response){
        if (user.getName().equals("admin") && user.getPassword().equals("1")){
            return "welcome";
        }
        Cookie cookie = new Cookie("setUser", user.getName());
        cookie.setMaxAge(5);
        response.addCookie(cookie);
        return "redirect:/login";
    }
}
