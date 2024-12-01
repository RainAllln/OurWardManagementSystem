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

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private int user_id;

    /*
    final修饰的变量必须在声明时或构造函数中初始化，初始化后不能再修改
    由于Patient,Doctor,Nurse都继承了User类，所以这里的变量是所有用户共享的
    每次使用的时候只需要让sqlSessionFactory.openSession()之后再获取mapper接口即可
    记得关闭连接
     */
    private static final String resource;   //静态变量，所有对象公用一个变量
    private static final InputStream inputStream;
    private static final SqlSessionFactory sqlSessionFactory;

    static{
        try {
            resource = "mybatis-config.xml";
            inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
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
            user_id = finded_user.getUser_id();     //获得用户id
            return "登录成功";
        } else {
            return "密码错误";
        }
    }

    private static String getMD5Str(String str) {
        //字符串转md5码
        byte[] digest = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest  = md5.digest(str.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //16是表示转换为16进制数
        return new BigInteger(1, digest).toString(16);
    }

    public void updatePassword(String newPassword){
        //更新密码
        SqlSession sqlSession = sqlSessionFactory.openSession();     //打开链接
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class); //获取mapper接口

        password = getMD5Str(newPassword);
        userMapper.updatePassword(user_id,password);

        sqlSession.commit(); //提交
        sqlSession.close(); //关闭连接
    }

}
