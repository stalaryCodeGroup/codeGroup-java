package com.stalary.codeGroup.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Peter on 2017-03-03.
 */
public class MapUtil {
    public static final String emptyStr = "";


    public static List<Map<String, Object>> convertToListHashMap(
            List<Object[]> resultList, String[] mapKeys) {

        if (resultList == null) {
            return new ArrayList<>(1);
        }

        List<Map<String, Object>> resultMap = new ArrayList<>(resultList.size());
        for (Object[] array : resultList) {
            resultMap.add(convertArrayToMapWithNotNull(mapKeys, array));
        }
        return resultMap;
    }

    public static Map<String, Object> convertArrayToMapWithNotNull(String[] keys, Object[] array) {
        if (array == null) {
            return new HashMap<>(1);
        }
        Map<String, Object> map = new HashMap<>(keys.length);
        for (int i = 0; i < keys.length; i++) {
            if (array[i] != null) {
                map.put(keys[i], array[i]);
            } else {
                map.put(keys[i], emptyStr);
            }
        }
        return map;
    }

    // 实体类转成 HashMap<String, Object> 对象
    public static HashMap<String, Object> beanToMap(Object obj) {
        if(obj == null){
            return null;
        }
        HashMap<String, Object> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : propertyDescriptors) {
                String key = property.getName();
                // 过滤class属性
                if (!key.equals("class")) {
                    // 得到property对应的getter方法
                    Method getter = property.getReadMethod();
                    Object value = getter.invoke(obj);
                    map.put(key, value);
                }
            }
        } catch (Exception e) {
            return null;
        }
        return map;
    }


}
