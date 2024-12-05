package team.jnu.wardsystem.mapper;

import org.apache.ibatis.annotations.Param;
import team.jnu.wardsystem.pojo.Doctor;
import team.jnu.wardsystem.pojo.Equipment;
import team.jnu.wardsystem.pojo.Patient;

import java.util.List;

public interface DoctorMapper {
    int updatePhone(@Param("doctor_id") int doctor_id, @Param("phone") String phone);

    Doctor searchDoctorById(int doctorId);

    String searchDepartmentName(int department_id);

}
