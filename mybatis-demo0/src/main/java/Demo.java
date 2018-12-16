import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.entity.TUser;
import com.example.mapper.TUserMapper;

public class Demo {


    public static void main(String[] args) throws IOException {

        String resource = "mybatis.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        inputStream.close();

        SqlSession sqlSession = sqlSessionFactory.openSession();
        TUserMapper mapper = sqlSession.getMapper(TUserMapper.class);

        System.err.println("查询Id<100的用户！！！");
        for (int i = 1; i < 100; i++) {
            TUser user = mapper.selectByPrimaryKey(i);
            if (user != null) {
                System.out.println(user.toString());
            }
        }

        System.err.println("查询所有用户信息！！！");
        List<TUser> users = mapper.selectAllUsers();
        for (TUser user : users) {
            System.out.println(user);
        }
    }
}
