package team.jnu.wardsystem.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import team.jnu.wardsystem.mapper.BedMapper;
import team.jnu.wardsystem.mapper.EquipmentMapper;

import java.io.InputStream;
import java.util.List;

@Data
@Setter
@Getter
@NoArgsConstructor
public class Bed {
  private int bed_id;
  private int ward_id;
  private boolean in_use;
  private boolean clean;
  private int nurse_id;
  private List<Equipment> equipmentList;

  public boolean searchEquipmentList(){
    // 根据bed_id查询equipmentList
    SqlSession sqlSession = User.sqlSessionFactory.openSession(); // 打开链接
    EquipmentMapper equipmentMapper = sqlSession.getMapper(EquipmentMapper.class); // 获取mapper接口
    equipmentList = equipmentMapper.searchEquipmentList(bed_id,ward_id); // 获取equipmentList
    sqlSession.close(); // 关闭连接
    return true;
  }
}