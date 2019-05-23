package com.zei.happy.service;

import com.github.pagehelper.PageInfo;
import com.zei.happy.domain.Evaluate;

import java.util.List;

public interface EvaluateService {

    List<Evaluate> findAll();

    Evaluate getOne(int id);

    Evaluate save(Evaluate evaluate);

    Evaluate update(Evaluate evaluate);

    Boolean delete(int id);

    PageInfo<Evaluate> findByCompany(int companyId, int pageNo, int pageSize);

    PageInfo<Evaluate> findByUser(int friendI, int pageNo, int pageSize);
}
