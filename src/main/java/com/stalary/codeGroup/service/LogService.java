package com.stalary.codeGroup.service;

import com.stalary.codeGroup.entity.Log;
import com.stalary.codeGroup.repo.LogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author:Stalary
 * @Description:日志业务层，操作日志
 * @Date Created in 下午5:07 17/8/24
 */
@Service
public class LogService extends BaseService<Log, LogRepo>{

    @Autowired
    public LogService(LogRepo repo) {
        super(repo);
    }

    public void create(String content){
        Log log = new Log();
        log.setContent(content);
        this.save(log);
    }
}
