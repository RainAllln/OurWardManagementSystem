package team.jnu.wardsystem.pojo;

import lombok.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import team.jnu.wardsystem.mapper.UserMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;


public class User {
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private int user_id;
    private final String resource;
    private final InputStream inputStream;
    private final SqlSessionFactory sqlSessionFactory;

    public User(String username, String password){
        this.username = username;
        this.password = password;
        try {
            this.resource = "mybatis-config.xml";
            this.inputStream = Resources.getResourceAsStream(resource);
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public User(){
        try {
            this.resource = "mybatis-config.xml";
            this.inputStream = Resources.getResourceAsStream(resource);
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean Login(String username,String password){

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        String confirmPassword = userMapper.confirmPassword(username);

        sqlSession.close();

        return confirmPassword.equals(password);
    }


}
