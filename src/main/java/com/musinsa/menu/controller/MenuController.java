package com.musinsa.menu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.musinsa.menu.service.MenuService;


@RestController
@CrossOrigin
@RequestMapping("/menu")
public class MenuController {
    
    @Resource
    private MenuService menuService;

    @RequestMapping(method = RequestMethod.GET, path = "/list")
    @ResponseBody
    public List<Map<String, Object>> getMenuAllList(@RequestParam Map<String, Object> param){

        return menuService.getMenuAllList(param);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/list/child")
    @ResponseBody
    public List<Map<String, Object>> getMenuList(@RequestParam Map<String, Object> param){

        return menuService.getMenuList(param);
    }

    
    @RequestMapping(method = RequestMethod.POST, path = "/sign")
    @ResponseBody
    public Map<String, Object> insertMenu(@RequestBody Map<String, Object> param){
        //#{name}, #{level}, #{parent_id}, #{parent_name}, #{path}
        Optional<String> sName = Optional.ofNullable(StringUtils.defaultString((String)param.get("name")));
        Optional<String> sLevel = Optional.ofNullable(StringUtils.defaultString((String)param.get("level")));

        Map<String, Object> mError = new HashMap<String, Object>();
        if(!sName.isPresent()){
            mError.put("errorMsg", "메뉴명을 입력해주세요.");
            return mError;
        }

        if(!sLevel.isPresent()){
            param.put("level", 1);
        }

        String sId = menuService.insertMenu(param);

        if(sId == ""){
            mError.put("errorMsg", "정상적으로 등록되지 않았습니다.");
            return mError;
        }

        param.put("id", sId);
        return menuService.getMenu(param);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/retouch")
    @ResponseBody
    public Map<String, Object> updateMenu(@RequestBody Map<String, Object> param){
        //#{name}, #{path}
        Optional<String> sId = Optional.ofNullable(StringUtils.defaultString((String)param.get("id")));
        Optional<String> sName = Optional.ofNullable(StringUtils.defaultString((String)param.get("name")));
        Optional<String> sPath = Optional.ofNullable(StringUtils.defaultString((String)param.get("path")));

        Map<String, Object> mError = new HashMap<String, Object>();

        if(!sId.isPresent()){
            mError.put("errorMsg", "수정할 메뉴의 ID가 없습니다");
            return mError;
        }

        if(!sPath.isPresent() || !sName.isPresent()){
            mError.put("errorMsg", "이름 또는 경로가 필요합니다.");
            return mError;
        }

        int nCnt = menuService.updateMenu(param);

        if(nCnt == 0){
            mError.put("errorMsg", "정상적으로 수정되지 않았습니다.");
            return mError;
        }

        return menuService.getMenu(param);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/removal")
    @ResponseBody
    public List<Map<String, Object>> deleteMenu(@RequestBody Map<String, Object> param){
        //#{name}, #{path}
        Optional<String> sId = Optional.ofNullable(StringUtils.defaultString((String)param.get("id")));

        List<Map<String, Object>> mError = new ArrayList<Map<String, Object>>();
        Map<String, Object> mErrorMsg = new HashMap<String, Object>();

        if(!sId.isPresent()){
            mError.clear();
            mErrorMsg.put("errorMsg", "삭제할 메뉴의 ID를 확인해주세요.");
            mError.add(mErrorMsg);
            return mError;
        }

        int nCnt = menuService.deleteMenu(param);

        if(nCnt == 0){
            mError.clear();
            mErrorMsg.put("errorMsg", "정상적으로 삭제되지 않았습니다.");
            mError.add(mErrorMsg);
            return mError;
        }

        return menuService.getMenuList(param);
    }
}
