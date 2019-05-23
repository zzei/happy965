package com.zei.happy.controller;

import com.github.pagehelper.PageInfo;
import com.zei.happy.domain.Company;
import com.zei.happy.domain.JsonData;
import com.zei.happy.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    /**
     * 添加企业
     * @param company
     * @return
     */
    @PostMapping("/add")
    public JsonData add(Company company, HttpServletRequest request, HttpServletResponse response){
        //获取header
        String token = request.getHeader("token");


        //验证非空
        if(StringUtils.isEmpty(company.getName())){
            return JsonData.buildError("企业名称不得为空！");
        }else if(company.getCreateUser() == null){
            return JsonData.buildError("请登录！");
        }

        Company addCompany = companyService.save(company);
        if(addCompany != null){
            return JsonData.buildSuccess(addCompany,"添加成功！");
        }
        return JsonData.buildError("添加失败！");
    }

    /**
     * 按id查找企业
     * @return
     */
    @GetMapping("/{id}")
    public JsonData getOne(@PathVariable("id") int id, HttpServletRequest request, HttpServletResponse response,
                           @CookieValue(value = "company", required = false) String cookieCompany){
        System.out.println("+++++++++"+cookieCompany+"+++++++++");

        String cookieStr = "";
        Cookie[] cookies = request.getCookies();
        if(cookies!=null){
            for (Cookie cookie : cookies){
                if("company".equals(cookie.getName())){
                    cookieStr = cookie.getValue();
                }
            }
        }
        System.out.println("-------"+cookieStr+"-------");

        Company company = companyService.getOne(id);
        if(company != null){
            Cookie cookie = new Cookie("company",company.getName());
            cookie.setPath("/");
            cookie.setMaxAge(5*60);
            response.addCookie(cookie);

            return JsonData.buildSuccess(company,"ok");
        }
        return JsonData.buildError("查无此企业");
    }

    @GetMapping("/find")
    public JsonData findByCondition(Company company,
                                    @RequestParam(value = "pageNo",required = false,defaultValue = "1") int pageNo,
                                    @RequestParam(value = "pageSize",required = false,defaultValue = "3") int pageSize){
        PageInfo<Company> companyList = companyService.findByCondition(company,pageNo,pageSize);
        return JsonData.buildSuccess(companyList,"ok");
    }

    @PostMapping("/update")
    public JsonData update(Company company){
        Company updateCompany = companyService.update(company);
        if(updateCompany != null){
            return JsonData.buildSuccess(updateCompany,"ok");
        }
        return JsonData.buildError("更新失败！");
    }

    /**
     * 审核企业
     * @return
     */
    @PostMapping("/review/{id}")
    public JsonData review(@PathVariable("id") int id){
        Company reviewCompany = new Company().setId(id).setState(1);
        reviewCompany = companyService.update(reviewCompany);
        if(reviewCompany != null){
            return JsonData.buildSuccess(reviewCompany,"ok");
        }
        return JsonData.buildError("审核失败！");
    }
}
