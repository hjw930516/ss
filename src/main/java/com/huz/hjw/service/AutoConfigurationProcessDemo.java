package com.huz.hjw.service;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.form.FormProperty;


import org.activiti.engine.form.TaskFormData;
import org.activiti.engine.impl.form.DateFormType;
import org.activiti.engine.impl.form.StringFormType;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class AutoConfigurationProcessDemo {
    private final static Logger log = LoggerFactory.getLogger(AutoConfigurationProcessDemo.class);
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private FormService formService;
    @Autowired
    private RepositoryService repositoryService;


    public void doProcess()  {
        //根据bpmn文件部署流程
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("processes/test.bpmn20.xml").deploy();
        //获取流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
        //启动流程定义，返回流程实例
        ProcessInstance pi = runtimeService.startProcessInstanceById(processDefinition.getId());
        String processId = pi.getId();
        System.out.println("流程创建成功，当前流程实例ID："+processId);

        Task task=taskService.createTaskQuery().processInstanceId(processId).singleResult();
        System.out.println("第一次执行前，任务名称："+task.getName());
        Map<String,Object> var = new HashMap<>();
        var.put("submitType","y");
        taskService.complete(task.getId(),var);

        task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
        System.out.println("第二次执行前，任务名称："+task.getName());
        var.put("tlApprove","y");
        taskService.complete(task.getId(),var);

        task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
        System.out.println("task为null，任务执行完毕："+task);


     /*   log.info("启动我们的程序");
        ProcessInstance processInstance = startProcessInstance("second_approve");
        try{
            processTask(processInstance);
        }catch (Exception e){
            e.getStackTrace();
        }
        log.info("结束我们的程序");*/
    }

    /**
     *  处理流程任务
     *
     *  @param processInstance
     *  @throws ParseException
     * */
    private void processTask(ProcessInstance processInstance) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        while (processInstance != null && !processInstance.isEnded()) {
            List<Task> list = taskService.createTaskQuery().list();
            log.info("待处理任务数量 [{}]", list.size());
            for (Task task : list) {
                log.info("待处理任务 [{}]", task.getName());
                Map<String, Object> variables = buildVariablesByScanner(scanner, task);
                taskService.complete(task.getId(), variables);
                processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();
            }         }
            scanner.close();
    }

    /**
     *  从控制台获取变量
     *  @param scanner
     *  @param task
     *  @return
     *  @throws ParseException
     * */
    private Map<String, Object> buildVariablesByScanner(Scanner scanner, Task task) throws ParseException {
        TaskFormData taskFormData = formService.getTaskFormData(task.getId());
        List<FormProperty> formProperties = taskFormData.getFormProperties();
        return buildVariablesByScanner(scanner, formProperties);
    }


    public static Map<String, Object> buildVariablesByScanner(Scanner scanner, List<FormProperty> formProperties) throws ParseException {
        Map<String, Object> variables = new HashMap<>();
        for (FormProperty property : formProperties) {
            String line = null;
            if (StringFormType.class.isInstance(property.getType())) {
                log.info("请输入 {} ？", property.getName());
                line = scanner.nextLine();
                variables.put(property.getId(), line);
            } else if (DateFormType.class.isInstance(property.getType())) {
                log.info("请输入 {} ？ 格式 （yyyy-MM-dd）", property.getName());
                line = scanner.nextLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = dateFormat.parse(line);
                variables.put(property.getId(), date);
            } else {
                log.info("类型暂不支持 {}", property.getType());
            }
            log.info("您输入的内容是 [{}]", line);
        }
        return variables;
    }



    /**
     * * 获取流程实例
     * * @param processDefinitionId
     * * @return
     * */
    private ProcessInstance startProcessInstance(String processDefinitionId) {
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(processDefinitionId);
        log.info("启动流程 [{}]", processInstance.getProcessDefinitionKey());
        return processInstance;
    }



}

