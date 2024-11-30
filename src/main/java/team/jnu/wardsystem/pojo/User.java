package team.jnu.wardsystem.pojo;

import lombok.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import team.jnu.wardsystem.mapper.UserMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

    public String Login(String username,String password){

        SqlSession sqlSession = sqlSessionFactory.openSession();     //打开链接
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class); //获取mapper接口

        User finded_user = userMapper.selectByUserName(username);
        if(finded_user == null) return "用户名不存在";//没有该用户名

        String confirmPassword = finded_user.getPassword();

        sqlSession.close(); //关闭连接

        if (confirmPassword.equals(getMD5Str(password))) {
            user_id = finded_user.getUser_id();
            return "登录成功";
        } else {
            return "密码错误";
        }
    }

    public static String getMD5Str(String str) {
        //字符串转md5码
        byte[] digest = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest  = md5.digest(str.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //16是表示转换为16进制数
        String md5Str = new BigInteger(1, digest).toString(16);
        return md5Str;
    }


}
