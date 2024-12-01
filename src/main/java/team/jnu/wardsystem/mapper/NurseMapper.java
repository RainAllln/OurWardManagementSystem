package team.jnu.wardsystem.mapper;

public interface NurseMapper {
    void setPassword(String password, String oldPassword);

    void setPhone(String phone);
}
