package com.zei.happy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zei.happy.domain.Company;
import com.zei.happy.mapper.CompanyMapper;
import com.zei.happy.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService{

    @Autowired
    CompanyMapper companyMapper;

    @Override
    public List<Company> findAll() {
        return companyMapper.findAll();
    }

    @Override
    public Company getOne(int id) {
        return companyMapper.getOne(id);
    }

    @Override
    public Company save(Company company) {
        company.setCreateTime(new Date());
        company.setState(0);        //发布企业，但未通过管理员审核
        if(companyMapper.save(company)>0){
            return company;
        }
        return null;
    }

    @Override
    public Company update(Company company) {
        if(companyMapper.update(company)>0){
            return company;
        }
        return null;
    }

    @Override
    public PageInfo<Company> findByCondition(Company company, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        PageHelper.orderBy("create_time DESC");

        List<Company> companyList = companyMapper.findByCondition(company);
        PageInfo<Company> pages = new PageInfo<>(companyList);
        return pages;
    }
}
