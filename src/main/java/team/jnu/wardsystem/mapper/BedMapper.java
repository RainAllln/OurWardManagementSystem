package team.jnu.wardsystem.mapper;
import org.apache.ibatis.annotations.Param;
import team.jnu.wardsystem.pojo.Bed;
import team.jnu.wardsystem.pojo.Patient;

import java.util.List;

public interface BedMapper {
    void updateBedStatus(@Param("bed_id") int bedId,@Param("ward_id") int wardId,@Param("in_use") boolean b);

    List<Bed> searchAllBed(int nurse_id);
}
