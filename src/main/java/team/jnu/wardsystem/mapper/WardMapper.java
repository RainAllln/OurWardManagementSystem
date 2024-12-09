package team.jnu.wardsystem.mapper;

import team.jnu.wardsystem.pojo.Ward;

public interface WardMapper {
    Ward searchWardById(int wardId);

    String searchWardType(int wardId); // 判断病房男女
}
