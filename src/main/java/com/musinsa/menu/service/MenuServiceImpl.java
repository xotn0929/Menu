package com.musinsa.menu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.musinsa.menu.mapper.MenuMapper;

@Service
public class MenuServiceImpl implements MenuService{

    @Resource
    private MenuMapper menuMapper;

    @Override
    public Map<String, Object> getMaxLevel(Map<String, ?> param) {
        return menuMapper.getMaxLevel(param);
    }

    @Override
    public Map<String, Object> getMenu(Map<String, ?> param) {
        return menuMapper.getMenu(param);
    }
    @Override
    public Map<String, Object> getMenu(String sId) {
        Map<String, Object> mData = new HashMap<String, Object>();
        mData.put("parent_id", sId);
        return menuMapper.getMenu(mData);
    }

    @Override
    public List<Map<String, Object>> getMenuAllList(Map<String, ?> param) {
        return menuMapper.getMenuAllList(param);
    }

    @Override
    public List<Map<String, Object>> getMenuList(Map<String, ?> param) {
        return menuMapper.getMenuList(param);
    }

    @Override
    public String insertMenu(Map<String, ?> param) {
        int nCnt = menuMapper.insertMenu(param);
        String nKey = "";

        if(nCnt > 0){
            nKey = StringUtils.defaultString(String.valueOf(param.get("ID")));
        }
        return nKey;
    }

    @Override
    public int updateMenu(Map<String, ?> param) {
        return menuMapper.updateMenu(param);
    }

    @Override
    public int deleteMenu(Map<String, ?> param) {
        Map<String, Object> mData = menuMapper.getMaxLevel(param);
        int nMaxLevel = (int) mData.get("MAXLEVEL");

        int nCnt = menuMapper.deleteMenu(param);

        Map<String, Object> mLevel = new HashMap<String, Object>();
        if(nCnt > 0){
            for(int i=2; i <= nMaxLevel; i++){
                mLevel.put("level", i);
                menuMapper.deleteChildMenu(param);
            }
        }

        return nCnt;
    }
    
}
