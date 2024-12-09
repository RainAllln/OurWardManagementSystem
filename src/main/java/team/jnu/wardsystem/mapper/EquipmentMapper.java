package team.jnu.wardsystem.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import team.jnu.wardsystem.pojo.Equipment;

public interface EquipmentMapper {
    List<Equipment> searchAllEquipment();

    void updateEquipment(@Param("equipment_id") int equipmentId, @Param("bed_id") int bedId,
            @Param("ward_id") int wardId);

    void deleteEquipment(int equipmentId);

    List<Equipment> searchEquipmentList(@Param("bed_id") int bedId, @Param("ward_id") int wardId);
}
