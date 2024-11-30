package team.jnu.wardsystem.mapper;

import team.jnu.wardsystem.pojo.User;

import java.util.List;

public interface UserMapper {

    List<User> selectAll();
    User selectByUserName(String user_name);
    String confirmPassword(String user_name);

}
