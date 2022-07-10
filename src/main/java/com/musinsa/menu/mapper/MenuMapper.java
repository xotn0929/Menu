package com.musinsa.menu.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MenuMapper {
    
    public List<Map<String, Object>> getMenuAllList(Map<String, ?> param);

    public List<Map<String, Object>> getMenuList(Map<String, ?> param);

    public Map<String, Object> getMenu(Map<String, ?> param);

    public Map<String, Object> getMaxLevel(Map<String, ?> param);

    public int insertMenu(Map<String, ?> param);

    public int updateMenu(Map<String, ?> param);

    public int deleteMenu(Map<String, ?> param);

    public int deleteChildMenu(Map<String, ?> param);
}
