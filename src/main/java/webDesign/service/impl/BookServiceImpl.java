package webDesign.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webDesign.controller.Code;
import webDesign.dao.BookDao;
import webDesign.domain.Book;
import webDesign.exception.BusinessException;
import webDesign.exception.SystemException;
import webDesign.service.BookService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    public boolean save(Book book, HttpSession session) {
        int userId=(Integer) session.getAttribute("userId");
        book.setUserId(userId);
        bookDao.save(book);
        return true;
    }

    public boolean update(Book book) {
        bookDao.update(book);
        return true;

    }

    public boolean delete(Integer id) {
        bookDao.delete(id);
        return true;
    }

    public Book getById(Integer id) {
        //模拟业务异常，包装成自定义异常
//        if(id == 1){
//            throw new BusinessException(Code.BUSINESS_ERR,"请不要使用你的技术挑战我的耐性!");
//        }
//        //模拟系统异常，将可能出现的异常进行包装，转换成自定义异常
//        try{
//            int i = 1/0;
//        }catch (Exception e){
//            throw new SystemException(Code.SYSTEM_TIMEOUT_ERR,"服务器访问超时，请重试!",e);
//        }
        return bookDao.getById(id);
    }


    public List<Book> getAll(Integer userId,String bookName) {
        return bookDao.getAll(userId,bookName);
    }
}


