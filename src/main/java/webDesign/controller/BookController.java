package webDesign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import webDesign.dao.BookDao;
import webDesign.domain.Book;
import webDesign.service.BookService;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController{
    @Autowired
    private BookService bookService;

    @Autowired
    private BookDao bookDao;

    @PostMapping
    public Result save(@RequestBody Book book,HttpSession session) {
        boolean flag = bookService.save(book,session);
        return new Result(flag ? Code.SAVE_OK:Code.UPDATE_ERR,flag);
    }

    @PutMapping
    public Result update(@RequestBody Book book) {
        boolean flag = bookService.update(book);
        return new Result(flag ? Code.UPDATE_OK:Code.UPDATE_ERR,flag);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        boolean flag = bookService.delete(id);
        return new Result(flag ? Code.DELETE_OK:Code.DELETE_ERR,flag);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Book book = bookService.getById(id);
        Integer code = book != null ? Code.GET_OK : Code.GET_ERR;
        String msg = book != null ? "" : "数据查询失败，请重试！";
        return new Result(code,book,msg);
    }

    @GetMapping
    public Result getAll(HttpSession session,@RequestParam("bookName") String bookName) {
        List<Book> bookList=null;
         int roleId= (Integer) session.getAttribute("roleId");
         if(roleId==1){
             try {
                 bookList=bookDao.getBookAll(bookName);
             }catch (Exception e){
                 System.out.println(e.getMessage());
             }
             Integer code = bookList != null ? Code.GET_OK : Code.GET_ERR;
             String msg = bookList != null ? "" : "数据查询失败，请重试！";
             return new Result(code,bookList,msg);

         }else{
             int userId=(Integer) session.getAttribute("userId");
             bookList = bookService.getAll(userId,bookName);
             Integer code = bookList != null ? Code.GET_OK : Code.GET_ERR;
             String msg = bookList != null ? "" : "数据查询失败，请重试！";
             return new Result(code,bookList,msg);
         }
    }
}
