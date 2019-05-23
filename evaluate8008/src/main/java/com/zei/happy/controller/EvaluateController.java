package com.zei.happy.controller;

import com.github.pagehelper.PageInfo;
import com.zei.happy.domain.Evaluate;
import com.zei.happy.domain.JsonData;
import com.zei.happy.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluate")
public class EvaluateController {

    @Autowired
    EvaluateService evaluateService;

    @PostMapping("/add")
    public JsonData add(Evaluate evaluate){
        //非空验证
        if(StringUtils.isEmpty(evaluate.getContent())){
            return JsonData.buildError("发表内容不能为空！");
        }else if(evaluate.getCreateUser() == null){
            return  JsonData.buildError("请登录！");
        }
        Evaluate addEvaluate = evaluateService.save(evaluate);
        if(addEvaluate != null){
            return JsonData.buildSuccess(addEvaluate,"ok");
        }
        return JsonData.buildError("发表失败！");
    }

    @GetMapping("/findByCompany/{id}")
    public JsonData findByCompany(@PathVariable("id") int id,
                                  @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize){
        PageInfo<Evaluate> evaluateList = evaluateService.findByCompany(id,pageNo,pageSize);
        return JsonData.buildSuccess(evaluateList,"ok");
    }

    @GetMapping("/findByUser/{id}")
    public JsonData findByUser(@PathVariable("id") int id,
                                  @RequestParam(value = "pageNo", required = false, defaultValue = "1") int pageNo,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "1") int pageSize){
        PageInfo<Evaluate> evaluateList = evaluateService.findByUser(id,pageNo,pageSize);
        return JsonData.buildSuccess(evaluateList,"ok");
    }

    /**
     * 管理员删除评论
     * @param id
     * @return
     */
    @PostMapping("/delete/{id}")
    public JsonData delete(@PathVariable("id") int id){
        if(evaluateService.delete(id)){
            return JsonData.buildSuccess("删除成功！");
        }
        return JsonData.buildError("删除失败");
    }
}
