package com.jason.web.servlet.old;

import com.alibaba.fastjson.JSON;
import com.jason.mapper.BrandMapper;
import com.jason.pojo.Brand;
import com.jason.service.BrandService;
import com.jason.service.impl.BrandServiceimpl;
import com.jason.util.SqlSessionFactoryUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;

public class SelectAllServlet extends HttpServlet {
    private BrandService brandservice= new BrandServiceimpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Brand> brands = brandservice.SelectAll();
        System.out.println(request.getRequestURI());
        String brandsjson = JSON.toJSONString(brands);
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(brandsjson);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
