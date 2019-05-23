package com.zei.happy.service;

import com.github.pagehelper.PageInfo;
import com.zei.happy.domain.Company;

import java.util.List;

public interface CompanyService {

    List<Company> findAll();

    Company getOne(int id);

    Company save(Company company);

    Company update(Company company);

    PageInfo<Company> findByCondition(Company company, int pageNo, int pageSize);
}
