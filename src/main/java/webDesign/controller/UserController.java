package webDesign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import webDesign.dao.UserDao;
import webDesign.domain.User;
import webDesign.service.UserService;


import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao userDao;

    @GetMapping("/getUserAll")

    public Result getUserAll(HttpSession session,@RequestParam("username") String username) {
        List<User> bookList = new ArrayList<>();
        int roleId = (Integer) session.getAttribute("roleId");
        int userId = (Integer) session.getAttribute("userId");
        if (roleId == 1) {
            bookList = userService.getUserAll(username);
            Integer code = bookList != null ? Code.GET_OK : Code.GET_ERR;
            String msg = bookList != null ? "" : "数据查询失败，请重试！";
            return new Result(code, bookList, msg);
        } else {
            bookList = userDao.getUserAllById(userId);
            Integer code = bookList != null ? Code.GET_OK : Code.GET_ERR;
            String msg = bookList != null ? "" : "数据查询失败，请重试！";
            return new Result(code, bookList, msg);
        }

    }

    @PostMapping
    public Result save(@RequestBody User user) {
        boolean flag = userService.save(user);
        return new Result(flag ? Code.SAVE_OK : Code.UPDATE_ERR, flag);
    }

    @PutMapping
    public Result update(@RequestBody User user) {
        boolean flag = userService.update(user);
        return new Result(flag ? Code.UPDATE_OK : Code.UPDATE_ERR, flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean flag = userService.delete(id);
        return new Result(flag ? Code.DELETE_OK : Code.DELETE_ERR, flag);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        User book = userService.getById(id);
        Integer code = book != null ? Code.GET_OK : Code.GET_ERR;
        String msg = book != null ? "" : "数据查询失败，请重试！";
        return new Result(code, book, msg);
    }


}
