package team.jnu.wardsystem.mapper;

import team.jnu.wardsystem.pojo.Nurse;

public interface NurseMapper {
    void setPassword(String password, String oldPassword);

    void setPhone(String phone);

    Nurse searchNurseById(int nurseId);
}
