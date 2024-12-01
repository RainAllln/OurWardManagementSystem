package team.jnu.wardsystem.mapper;

import org.apache.ibatis.annotations.Param;
import team.jnu.wardsystem.pojo.Patient;

import java.util.List;

public interface PatientMapper {
    List<Patient> selectAllPatients();
    Patient searchPatientById(int patient_id);
    int updatePatientNote(@Param("patient_id") int patientId, @Param("notes") String notes);
}
