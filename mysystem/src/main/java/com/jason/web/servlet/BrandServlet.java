package com.jason.web.servlet;

import com.alibaba.fastjson.JSON;
import com.jason.pojo.Brand;
import com.jason.service.BrandService;
import com.jason.service.impl.BrandServiceimpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private BrandService brandservice= new BrandServiceimpl();
    public HttpServletResponse  crossinit(HttpServletResponse response){
        //设置响应头解决跨域问题
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
//        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, " +
                        "WG-App-Version, WG-Device-Id, WG-Network-Type, WG-Vendor, WG-OS-Type, " +
                        "WG-OS-Version, WG-Device-Model, WG-CPU, WG-Sid, WG-App-Id, WG-Token");
        return response;

    }

    public void SelectAll(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        //具体方法编写
        List<Brand> brands = brandservice.SelectAll();
        String brandsjson = JSON.toJSONString(brands);
        response = crossinit(response);
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(brandsjson);

    }

    public void add(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
        BufferedReader reader = request.getReader();
        String json = reader.readLine();
        Brand brand = JSON.parseObject(json, Brand.class);
        System.out.println("新增了数据对象"+brand);
        brandservice.add(brand);
        response = crossinit(response);
        response.getWriter().write("success");
    }

    public void BatchDelete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        response = crossinit(response);
        BufferedReader reader = request.getReader();
        String json = reader.readLine();
        System.out.println(json);
        //json转化为数组
        int[] ids = JSON.parseObject(json, int[].class);
        if(ids.length<1) {
            response.getWriter().write("false");
            return;
        }
        brandservice.BatchDelete(ids);
        response.getWriter().write("success");

    }

    public void SingleDelete(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        response = crossinit(response);
        BufferedReader reader = request.getReader();
        String json = reader.readLine();
        //json转化为整形
        int id = JSON.parseObject(json, int.class);
        System.out.println(id);
        brandservice.SingleDelete(id);
        response.getWriter().write("success");

    }

    public void Update(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        response = crossinit(response);
        request.setCharacterEncoding("UTF-8");
        BufferedReader reader = request.getReader();
        String json = reader.readLine();
        Brand brand = JSON.parseObject(json, Brand.class);
        System.out.println("修改了了数据对象"+brand);
        brandservice.Update(brand);
        response.getWriter().write("success");

    }

    public void SelectByCondition(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        response = crossinit(response);
        request.setCharacterEncoding("UTF-8");
        BufferedReader reader = request.getReader();
        String json = reader.readLine();
        System.out.println(json);
        Brand brand = JSON.parseObject(json, Brand.class);
        System.out.println(brand);
        List<Brand> brands = brandservice.SelectByCondition(brand.getBrandName(), brand.getCompanyName(), brand.getStatus());
        response.setContentType("text/html;charset=UTF-8");
        String brandsjson = JSON.toJSONString(brands);
        response.getWriter().write(brandsjson);
    }
}
