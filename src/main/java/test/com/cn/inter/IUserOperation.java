package test.com.cn.inter;

import test.com.cn.model.Book;
import test.com.cn.model.User;

import java.util.List;

/**
 * Created by wefit on 16/2/13.
 */
public interface IUserOperation {
    public User getUser(int id);
    public List<User> selectUsers();
    public void addUser(User user);
    public void updateUser(User user);
    public void deleteUser(User user);
    public List<Book> getUserBooks(int i);
}
