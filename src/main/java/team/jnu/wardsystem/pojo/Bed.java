package team.jnu.wardsystem.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import team.jnu.wardsystem.mapper.BedMapper;

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
}