package team.jnu.wardsystem.mapper;

import org.apache.ibatis.annotations.Param;
import team.jnu.wardsystem.pojo.Equipment;

import java.util.List;

public interface EquipmentMapper {
    List<Equipment> searchAllEquipment();

    void updateEquipment(@Param("equipment_id") int equipmentId,@Param("bed_id") int bedId,@Param("ward_id") int wardId);
}
