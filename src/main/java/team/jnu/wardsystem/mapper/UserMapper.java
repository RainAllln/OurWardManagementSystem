package team.jnu.wardsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import team.jnu.wardsystem.pojo.User;

public interface UserMapper {

    List<User> selectAll();

    User selectByUserName(String user_name);

    Boolean verifyPassword(@Param("user_name") String username_input,
            @Param("password") String encrypted_password_input);

    // void updatePassword(@Param("user_id") int user_id,@Param("password") String
    // password);
    int updatePassword(User user);

    void insertUser(User user);

    void deleteUser(int patientId);
}
