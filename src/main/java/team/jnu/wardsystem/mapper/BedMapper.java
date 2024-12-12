package team.jnu.wardsystem.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import team.jnu.wardsystem.pojo.Bed;

public interface BedMapper {
    void updateBedStatus(@Param("bed_id") int bedId, @Param("ward_id") int wardId, @Param("in_use") boolean b);

    int updateBedstatus(@Param("bed_id") int bedId, @Param("ward_id") int wardId, @Param("help") boolean b);

    List<Bed> searchAllBed(int nurse_id);

    List<Bed> searchUnassignedBed(int department_id);

    Bed searchBedById(@Param("bed_id") int bedId, @Param("ward_id") int wardId);

    int getNurseId(@Param("bed_id") int bedId, @Param("ward_id") int wardId);
}
