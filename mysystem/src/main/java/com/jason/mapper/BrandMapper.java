package com.jason.mapper;

import java.util.List;
import com.jason.pojo.Brand;
import org.apache.ibatis.annotations.*;

public interface BrandMapper {
    //查询所有数据
   @Select("select * from tb_brand")
   @ResultMap("brandResultMap")
     List<Brand> SelectAll();

   //条件查询
   List<Brand> SelectByCondition(@Param("brandName")String brandName,@Param("companyName")String companyName,@Param("status")int status);

   //增加数据
    @Insert("insert into tb_brand values (null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    //批量删除
    void  BatchDelete(@Param("ids") int[] ids);

    //单个删除
    @Delete("delete from tb_brand where id=#{id}")
    void SingleDelete(int id);

    //修改数据
    @Update("update tb_brand set brand_name=#{brandName},company_name = #{companyName}," +
            "ordered=#{ordered},description=#{description},status=#{status} where id=#{id}")
    void Update(Brand brand);
}
