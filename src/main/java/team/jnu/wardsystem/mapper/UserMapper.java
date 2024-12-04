package team.jnu.wardsystem.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import team.jnu.wardsystem.pojo.User;

import java.util.List;

public interface UserMapper {

    List<User> selectAll();
    User selectByUserName(String user_name);
    //void updatePassword(@Param("user_id") int user_id,@Param("password") String password);
    int updatePassword(User user);

    void insertUser(User user);
}
