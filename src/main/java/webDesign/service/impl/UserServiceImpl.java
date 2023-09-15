package webDesign.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webDesign.dao.BookDao;
import webDesign.dao.UserDao;
import webDesign.domain.User;
import webDesign.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean save(User user) {
         userDao.save(user);
         return  true;
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public List<User> getUserAll(String username) {
        return userDao.getUserAll(username);
    }

    @Override
    public boolean update(User user) {
        userDao.update(user);
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        userDao.delete(id);
        return true;
    }

    @Override
    public User getById(Integer id) {
        return userDao.getById(id);
    }
}
