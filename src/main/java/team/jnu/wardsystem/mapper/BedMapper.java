package team.jnu.wardsystem.mapper;
import org.apache.ibatis.annotations.Param;
import team.jnu.wardsystem.pojo.Bed;
public interface BedMapper {
    void updateBedStatus(@Param("bed_id") int bedId,@Param("ward_id") int wardId,@Param("in_use") boolean b);
}
