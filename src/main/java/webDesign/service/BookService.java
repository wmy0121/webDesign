package webDesign.service;

import org.springframework.transaction.annotation.Transactional;
import webDesign.domain.Book;

import javax.servlet.http.HttpSession;
import java.util.List;

@Transactional
    public interface BookService {
    /**
    *保 存
 	* @param book
 	* @return
    */
    public boolean save(Book book, HttpSession session);
    /**
 	* 修 改
 	* @param book
 	* @return
 	*/
        public boolean update(Book book);
        /**
 * 按id删除
 	* @param id
 	* @return
 	*/
        public boolean delete(Integer id);
        	/**
 	* 按id查询
 	* @param id
 	* @return
 	*/
        	public Book getById(Integer id);
        	/**
 	* 查询全部
 	* @return
             */
            public List<Book> getAll(Integer userId,String bookName);
}


