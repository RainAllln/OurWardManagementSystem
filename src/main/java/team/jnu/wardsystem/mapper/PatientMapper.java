package team.jnu.wardsystem.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import team.jnu.wardsystem.pojo.Patient;

public interface PatientMapper {
    List<Patient> selectAllPatients(int doctor_id);

    Patient searchPatient(@Param("bed_id") int bed_id, @Param("ward_id") int ward_id);

    Patient searchPatientById(int patient_id);

    int updatePatientNote(@Param("bed_id") int bed_id, @Param("ward_id") int ward_id, @Param("notes") String notes);

    void updatePatientBed(@Param("bed_id") int bedId, @Param("ward_id") int wardId, @Param("newBedId") int newBedId);

    void updatePatientWard(@Param("bed_id") int bedId, @Param("ward_id") int wardId, @Param("newWardId") int newWardId);

    int getMaxPatientID();

    void insertPatient(Patient patient);

    void deletePatient(@Param("bed_id") int bedId, @Param("ward_id") int wardId);

    List<Patient> searchUnassignedPatient();

    void updatePatientStatus(Patient patient);

    double getPaidAmount(int patientId);

    void updatePatientPhone(@Param("patient_id") int patientId, @Param("new_phone") String newPhone);

    void updatePaidAmount(@Param("patient_id") int patientId, @Param("paid_amount") double paidAmount);
}
