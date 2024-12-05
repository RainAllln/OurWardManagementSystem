package team.jnu.wardsystem.mapper;

import org.apache.ibatis.annotations.Param;
import team.jnu.wardsystem.pojo.Nurse;
import team.jnu.wardsystem.pojo.Patient;

import java.util.List;

public interface PatientMapper {
    List<Patient> selectAllPatients(int doctor_id);

    Patient searchPatientById(int patient_id);

    int updatePatientNote(@Param("patient_id") int patientId, @Param("ward_id") int ward_id,
            @Param("notes") String notes);

    void updatePatientBed(@Param("bed_id") int bedId, @Param("ward_id") int wardId, @Param("newBedId") int newBedId);

    void updatePatientWard(@Param("bed_id") int bedId, @Param("ward_id") int wardId, @Param("newWardId") int newWardId);

    int getMaxPatientID();

    void insertPatient(Patient patient);

    void deletePatient( @Param("bed_id")int bedId, @Param("ward_id")int wardId);

    List<Patient> searchUnassignedPatient();


    void updatePatientStatus(Patient patient);

    double getPaidAmount(int patientId);
}
