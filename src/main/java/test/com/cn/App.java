package test.com.cn;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import test.com.cn.inter.IUserOperation;
import test.com.cn.model.Book;
import test.com.cn.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //mybatis的配置文件
        String resource = "config.xml";
        //使用类加载器加载mybatis的配置文件（它也加载关联的映射文件）
        //InputStream is = App.class.getClassLoader().getResourceAsStream(resource);
        //构建sqlSession的工厂
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
        }catch(IOException e){

        }
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
        //Reader reader = Resources.getResourceAsReader(resource);
        //构建sqlSession的工厂
        //SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //创建能执行映射文件中sql的sqlSession
        SqlSession session = sessionFactory.openSession();

        /**
         * 映射sql的标识字符串，
         * me.gacl.mapping.userMapper是userMapper.xml文件中mapper标签的namespace属性的值，
         * getUser是select标签的id属性值，通过select标签的id属性值就可以找到要执行的SQL
         */
        String statement = "me.gacl.mapping.userMapper.getUser";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
        //User user = session.selectOne(statement, 3234);
        IUserOperation userOperation=session.getMapper(IUserOperation.class);
        User user = userOperation.getUser(3);
        List<User> users = userOperation.selectUsers();
        for(User u: users){
            System.out.println(u);
        }

        User newUser = new User();
        newUser.setName("tulianghui");
        newUser.setId(7968);
        userOperation.addUser(newUser);
        newUser.setId(3);
        newUser.setName("xxx");
        userOperation.updateUser(newUser);
        userOperation.deleteUser(newUser);
        List<Book> books = userOperation.getUserBooks(3234);
        for(Book book:books) {
            System.out.println(book.getBookname() + book.getUser().getName());
        }
        session.commit();
        session.close();

    }
}
