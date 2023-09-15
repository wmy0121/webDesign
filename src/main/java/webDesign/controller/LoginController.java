package webDesign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import webDesign.config.Token;
import webDesign.domain.User;
import webDesign.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/pages/index.html";
    }

    @RequestMapping("/userHtml")
    public  String goUser(){
        return "redirect:/pages/user.html";
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password, HttpSession session) {
        User user = userService.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            String token="";
            try {
                 token = new Token().generateToken(user);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            session.setAttribute("token", token);
            try {
                int userId = new Token().extractUserId(token);
                int roleId=new Token().extractRoleId(token);
                session.setAttribute("userId", userId);
                session.setAttribute("roleId", roleId);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            return "redirect:/pages/books.html";
        } else {
            return "redirect:/login?error";
        }
    }


    @RequestMapping("/goToUser")
    public  String userIndex(HttpSession session){
        int roleId= (Integer) session.getAttribute("roleId");
        return "redirect:/pages/user.html?roleId="+roleId;
    }

}
