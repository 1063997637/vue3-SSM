package com.jason.service.impl;

import com.jason.mapper.BrandMapper;
import com.jason.pojo.Brand;
import com.jason.service.BrandService;
import com.jason.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceimpl implements BrandService {
    @Override
    public List<Brand> SelectAll() {
        //1.加载mybatis核心配置文件，获取sqlSessionFactory//官网有

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //2.获取SqlSession对象,用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.获取UserMapper接口的代理对象
        BrandMapper brandmapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = brandmapper.SelectAll();

        //4.释放资源
        sqlSession.close();
        return brands;
    }

    @Override
    public void add(Brand brand) {
        //1.加载mybatis核心配置文件，获取sqlSessionFactory

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //2.获取SqlSession对象,用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.获取UserMapper接口的代理对象
        BrandMapper brandmapper = sqlSession.getMapper(BrandMapper.class);
        brandmapper.add(brand);

        //增删改记得提交事务
        sqlSession.commit();

        //4.释放资源
        sqlSession.close();
    }

    @Override
    public void BatchDelete(int[] ids) {
        //1.加载mybatis核心配置文件，获取sqlSessionFactory

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //2.获取SqlSession对象,用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.获取UserMapper接口的代理对象
        BrandMapper brandmapper = sqlSession.getMapper(BrandMapper.class);
        brandmapper.BatchDelete(ids);

        //增删改记得提交事务
        sqlSession.commit();

        //4.释放资源
        sqlSession.close();
    }

    @Override
    public void SingleDelete(int id) {
        //1.加载mybatis核心配置文件，获取sqlSessionFactory

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //2.获取SqlSession对象,用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.获取UserMapper接口的代理对象
        BrandMapper brandmapper = sqlSession.getMapper(BrandMapper.class);
        brandmapper.SingleDelete(id);

        //增删改记得提交事务
        sqlSession.commit();

        //4.释放资源
        sqlSession.close();
    }

    @Override
    public void Update(Brand brand) {
        //1.加载mybatis核心配置文件，获取sqlSessionFactory

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //2.获取SqlSession对象,用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.获取UserMapper接口的代理对象
        BrandMapper brandmapper = sqlSession.getMapper(BrandMapper.class);
        brandmapper.Update(brand);

        //增删改记得提交事务
        sqlSession.commit();

        //4.释放资源
        sqlSession.close();
    }

    @Override
    public List<Brand> SelectByCondition(String brandName, String companyName, int status) {
        //1.加载mybatis核心配置文件，获取sqlSessionFactory//官网有

        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

        //2.获取SqlSession对象,用它来执行sql
        SqlSession sqlSession = sqlSessionFactory.openSession();

        //3.获取UserMapper接口的代理对象
        BrandMapper brandmapper = sqlSession.getMapper(BrandMapper.class);
        List<Brand> brands = brandmapper.SelectByCondition(brandName,companyName,status);

        //4.释放资源
        sqlSession.close();
        return brands;
    }
}
