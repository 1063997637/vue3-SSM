package com.jason.service;

import com.jason.pojo.Brand;

import java.util.List;

public interface BrandService {
    /*
    *查询所有
     */
    List<Brand> SelectAll();

    void add(Brand brand);

    void  BatchDelete(int[] ids);

    void SingleDelete(int id);

    void Update(Brand brand);

    List<Brand> SelectByCondition(String brandName,String companyName,int status);
}
