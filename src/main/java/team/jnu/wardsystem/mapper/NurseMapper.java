package team.jnu.wardsystem.mapper;

import org.apache.ibatis.annotations.Param;
import team.jnu.wardsystem.pojo.Nurse;

public interface NurseMapper {
    int updatePhone(@Param("nurse_id") int nurse_id, @Param("phone") String phone);

    String searchDepartmentName(int department_id);

    Nurse searchNurseById(int nurse_id);
}
