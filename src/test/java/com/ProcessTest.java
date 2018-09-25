package com;

import com.huz.hjw.BootsyApplication;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BootsyApplication.class)
public class ProcessTest {
    private final static Logger logger = LoggerFactory.getLogger(ProcessTest.class);
    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private IdentityService identityService;

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private ProcessEngine processEngine;

    @Resource
    private HistoryService historyService;
    @Test
    public void doProcess(){
//        repositoryService.createDeployment()
//                .addClasspathResource("processes/process.bpmn20.xml")
//                .deploy();
//        Spring boot中自动部署,所以不需要先部署,直接就启动流程

        identityService.setAuthenticatedUserId("dakeng");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("process","busi_num_5");
        //流程实例启动后，流程会跳转到请假申请节点
        //通过实例ID来查询任务ID
        String pistId = processInstance.getId();
        Task vacationApply = taskService.createTaskQuery().processInstanceId(pistId).singleResult();
        taskService.setAssignee(vacationApply.getId(), "dakeng");

        Map<String, Object> args = new HashMap<>();
        args.put("time", "20180925");
        args.put("content", "休息");
        taskService.complete(vacationApply.getId(), args);

        //一级审批
        doTask(pistId,"0","gonzo");

       //二级审批
//        doTask(pistId,"1","gonzo");
    }

    public void doTask(String pistId,String flag,String psnId){
        //查询当前审批节点
        Task vacationAudit = taskService.createTaskQuery().processInstanceId(pistId).singleResult();
        Map<String, Object> args = new HashMap<>();
        args.put("approve", flag);
        //设置审批任务的执行人
       // taskService.claim(vacationAudit.getId(), psnId);
        //完成审批任务
        taskService.complete(vacationAudit.getId(), args);
    }

}
