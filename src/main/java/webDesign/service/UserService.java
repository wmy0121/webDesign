package webDesign.service;

import org.springframework.transaction.annotation.Transactional;
import webDesign.domain.Book;
import webDesign.domain.User;

import java.util.List;

@Transactional
public interface UserService {

     boolean save(User user);

     User findByUsername(String username);

     List<User> getUserAll(String username);

    public boolean update(User user);

    public boolean delete(Integer id);
    public User getById(Integer id);
}
