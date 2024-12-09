package team.jnu.wardsystem.pojo;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import team.jnu.wardsystem.mapper.UserMapper;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    protected String username;
    protected String password;
    private String role;
    private int user_id;

    /*
        * 静态变量，所有对象共享一个变量
        * 用于连接数据库
        * 每个角色用不同的密码登录
     */

    private static final String resource; // 静态变量，所有对象公用一个变量
    private static final InputStream inputStream;
    private static final SqlSessionFactory sqlSessionFactory;

    static {
        try {
            resource = "mybatis-config.xml";
            inputStream = Resources.getResourceAsStream(resource);
//            String key = "WnzsbFGY2aMQDEPKBBZ1+w=="; // Use the same key as used for encryption

            // Decrypt the encrypted values
//            String encryptedUrl = "LTvr7at4spXuMU+Psg9E4ZQolqDM2Dlnro0fzjDuaEt+aKqAwR/txiCJB0kiTxCi";
//            String encryptedUsername = "fclzmIrNceSdbh7KrXEN4w==";
//            String encryptedPassword = "Hh//0vMaQvqyixoMtoTnlA==";

            String url = "jdbc:postgresql://113.45.207.38:26000/ward";
            String DBusername = "gaussdb";
            String DBpassword = "Gauss123";
//            try {
//                url = EncryptionUtil.decrypt(encryptedUrl, key);
//                DBusername = EncryptionUtil.decrypt(encryptedUsername, key);
//                DBpassword = EncryptionUtil.decrypt(encryptedPassword, key);
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }

            // Configure the data source with decrypted values
            PooledDataSource dataSource = new PooledDataSource();
            dataSource.setDriver("org.postgresql.Driver");
            dataSource.setUrl(url);
            dataSource.setUsername(DBusername);
            dataSource.setPassword(DBpassword);

            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            sqlSessionFactory.getConfiguration().setEnvironment(new org.apache.ibatis.mapping.Environment("development",
                    sqlSessionFactory.getConfiguration().getEnvironment().getTransactionFactory(), dataSource));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public User(String username, int user_id, String role) {
        this.username = username;
        this.user_id = user_id;
        this.role = role;
    }

    public String Login(String username, String password) {

        SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class); // 获取mapper接口

        User found_user = userMapper.selectByUserName(username);
        if (found_user == null)
            return "用户名不存在";// 没有该用户名

        Boolean login_state = userMapper.verifyPassword(username, getMD5Str(password)); // 验证密码

        sqlSession.close(); // 关闭连接

        if (login_state) {
            user_id = found_user.getUser_id(); // 获得用户id
            role = found_user.getRole(); // 获得用户角色
            return "登录成功";
        } else {
            return "密码错误";
        }
    }

    public boolean isExistUser(String user_name) {
        // 查询用户是否已存在
        SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class); // 获取mapper接口
        User finded_user = userMapper.selectByUserName(user_name); // 获取用户列表
        sqlSession.close(); // 关闭连接
        return finded_user != null;
    }

    public static String getMD5Str(String str) {
        // 字符串转md5码
        byte[] digest = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest = md5.digest(str.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 16是表示转换为16进制数
        return new BigInteger(1, digest).toString(16);
    }

    public void updatePassword(String newPassword) {
        // 更新密码
        SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class); // 获取mapper接口
        password = getMD5Str(newPassword);
        // userMapper.updatePassword(user_id,password);
        userMapper.updatePassword(this);
        sqlSession.commit(); // 提交
        sqlSession.close(); // 关闭连接
    }

    public void register() {
        SqlSession sqlSession = sqlSessionFactory.openSession(); // 打开链接
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class); // 获取mapper接口
        userMapper.insertUser(this);
        sqlSession.commit(); // 提交
        sqlSession.close(); // 关闭连接
    }
}
