package com.stalary.codeGroup.service;

import com.stalary.codeGroup.util.MapUtil;
import com.stalary.codeGroup.util.TenantUtils;
import com.stalary.codeGroup.viewmodel.PageModel;
import com.stalary.codeGroup.repo.BaseRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
/**
 * @Author:Stalary
 * @Description:
 * @Date Created in 下午6:07 17/7/25
 */
public abstract class BaseService<T, R extends BaseRepo<T, Integer>> {

    protected R repo;

    protected int tenantId = TenantUtils.getTenantId();

    protected BaseService(R repo) {
        this.repo = repo;
    }


    public T findOne(Integer id) {
        return repo.findOne(id);
    }

    public T save(T entity) {

        return repo.save(entity);
    }

    public void save(List<T> entityList) {
        repo.save(entityList);
    }

    public void deleteById(Integer id) {
        repo.delete(id);
    }


    protected Map<String, Object> getMap(String[] keys, Object object) {
        return MapUtil.convertArrayToMapWithNotNull(keys, (Object[]) object);
    }

    public PageModel<T> getPageModel(Page<T> page, Pageable pageable) {
        PageModel<T> result = new PageModel<>(page.getContent(), page.getTotalPages());
        long index = page.getTotalElements() - pageable.getOffset();
        result.setIndex((int) index);
        return result;
    }

    // 动态条件查询时的范围查询拆分
    public String scopeJudgment(String column, String scope, String delimit) {
        String min;
        String max;
        try {
            String[] scopes = scope.split(delimit, 2);
            if (scopes.length > 1){
                min = scopes[0];
                max = scopes[1].contains("岁")?scopes[1].substring(0,scopes[1].indexOf("岁")):scopes[1];
                return "and " + column + " BETWEEN '" + min + "' AND '" + max + "' ";
            }else{
                if (scope.contains("岁以上")){
                    min = scope.substring(0,scope.indexOf("岁以上"));
                    return "and " + column + " >= " + min +" ";
                }
            }
            return "";
        } catch (Exception e) {
            return "";
        }

    }



}