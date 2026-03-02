package com.joaopscalazans.restaurante_api.infra.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;


public class ObjectUtils {

    public static void copyNonNullProperties(Object source,Object target){
        BeanUtils.copyProperties(source, target, getNullPropertiesNames(source));
    }

    private static String[] getNullPropertiesNames(Object source){
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for(PropertyDescriptor pd: pds){
            var name = src.getPropertyValue(pd.getName());
            if(name == null) emptyNames.add(pd.getName());
        }
        return emptyNames.toArray(new String[0]);
    }
    

}
