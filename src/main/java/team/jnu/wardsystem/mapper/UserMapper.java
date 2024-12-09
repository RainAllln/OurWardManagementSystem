package team.jnu.wardsystem.mapper;

import org.apache.ibatis.annotations.Param;
import team.jnu.wardsystem.pojo.User;

public interface UserMapper {
    User selectByUserName(String user_name);

    Boolean verifyPassword(@Param("user_name") String username_input,
            @Param("password") String encrypted_password_input);

    int updatePassword(User user);

    void insertUser(User user);

    void deleteUser(int patientId);
}
