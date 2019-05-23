package com.zei.happy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zei.happy.domain.Evaluate;
import com.zei.happy.mapper.EvaluateMapper;
import com.zei.happy.service.EvaluateService;
import com.zei.happy.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EvaluateServiceImpl implements EvaluateService{

    @Autowired
    EvaluateMapper evaluateMapper;

    @Override
    public List<Evaluate> findAll() {
        return evaluateMapper.findAll();
    }

    @Override
    public Evaluate getOne(int id) {
        return evaluateMapper.getOne(id);
    }

    @Override
    public Evaluate save(Evaluate evaluate){
        evaluate.setCreateTime(new Date());
        evaluate.setState(1);
        if(evaluateMapper.save(evaluate)>0){
            return evaluate;
        }
        return null;
    }

    @Override
    public Evaluate update(Evaluate evaluate) {
        if(evaluateMapper.update(evaluate)>0){
            return evaluate;
        }
        return null;
    }

    @Override
    public Boolean delete(int id) {
        Evaluate evaluate = new Evaluate().setId(id).setState(0);
        if(evaluateMapper.update(evaluate)>0){
            return true;
        }
        return false;
    }

    @Override
    public PageInfo<Evaluate> findByCompany(int companyId, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        PageHelper.orderBy("create_time ASC");

        List<Evaluate> evaluateList = evaluateMapper.findByCompany(companyId);
        for (Evaluate evaluate: evaluateList) {
            evaluate.setDateStr(DateUtils.dateToStr(evaluate.getCreateTime()));
        }

        PageInfo pages = new PageInfo(evaluateList);
        return pages;
    }

    @Override
    public PageInfo<Evaluate> findByUser(int friendI, int pageNo, int pageSize) {
        PageHelper.startPage(pageNo,pageSize);
        PageHelper.orderBy("create_time DESC");

        List<Evaluate> evaluateList = evaluateMapper.findByUser(friendI);
        PageInfo pages = new PageInfo(evaluateList);
        return pages;
    }
}
