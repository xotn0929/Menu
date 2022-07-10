package com.musinsa.menu.service;

import java.util.List;
import java.util.Map;


public interface MenuService {
    
    // 전체 메뉴리스트 검색
    public List<Map<String, Object>> getMenuAllList(Map<String, ?> param);

    // 메뉴 리스트 조회
    public List<Map<String, Object>> getMenuList(Map<String, ?> param);

    // 메뉴 단일조회
    public Map<String, Object> getMenu(Map<String, ?> param);
    public Map<String, Object> getMenu(String sId);

    // 메뉴 최대 레벨 조회
    public Map<String, Object> getMaxLevel(Map<String, ?> param);

    // 등록
    public String insertMenu(Map<String, ?> param);

    // 수정
    public int updateMenu(Map<String, ?> param);

    // 삭제
    public int deleteMenu(Map<String, ?> param);

}
