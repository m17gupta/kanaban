package com.capstoneproject.taskdata.service;

import com.capstoneproject.taskdata.domain.*;
import com.capstoneproject.taskdata.exception.*;
import com.capstoneproject.taskdata.proxy.EmployeeProxy;
import com.capstoneproject.taskdata.rabbitMQ.EmailDetails;
import com.capstoneproject.taskdata.rabbitMQ.Producer;
import com.capstoneproject.taskdata.repository.EmployeeDataRepository;
import org.bouncycastle.asn1.cmp.PKIStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeDataServiceImpl implements EmployeeDataService {

    @Autowired
    private EmployeeDataRepository employeeDataRepository;

    @Autowired
    private EmployeeProxy employeeProxy;

    @Autowired
    private Producer producer;



    @Override
    public EmployeeData addUser(CommonUser commonUser) throws EmployeeAlreadyExistsException {

        ResponseEntity<?> response = employeeProxy.sendEmployeeObjectToAuthApp(commonUser);
        System.out.println(response);

        if (employeeDataRepository.findById(commonUser.getEmployeeName()).isPresent()) {
            throw new EmployeeAlreadyExistsException();
        } else {
            EmployeeData employeeData = new EmployeeData(commonUser.getEmailId(), commonUser.getEmployeeName(), commonUser.getRole(), new ArrayList<Project>());
            return employeeDataRepository.insert(employeeData);
        }

    }

    @Override
    public EmployeeData addProject(String emailId, Project project) throws ProjectAlreadyExistsException, EmployeeNotFoundException {
        if (employeeDataRepository.findById(emailId).isPresent()) {
            EmployeeData data = employeeDataRepository.findById(emailId).get();
            Project p1 = new Project(project.getProjectId(), project.getProjectName(), project.getDuration(), new ArrayList<Column>());
            if (data.getProjects().stream().anyMatch(i -> i.getProjectId().equalsIgnoreCase(p1.getProjectId()))) {
                throw new ProjectAlreadyExistsException();
            } else {
                data.getProjects().add(p1);
//
                System.out.println(p1);
                return employeeDataRepository.save(data);
            }

        }
        throw new EmployeeNotFoundException();
    }


    @Override
    public Project getProjectById(String email, String projectId) {
        List<EmployeeData> employeeDataList = employeeDataRepository.findAll();
        for (int i = 0; i < employeeDataList.size(); i++) {
            EmployeeData employeeData = employeeDataList.get(i);

            if (employeeData.getEmailId().equalsIgnoreCase(email)) {
                List<Project> projectLIst = employeeData.getProjects();
                if (projectLIst != null) {
                    Project project = projectLIst.stream().filter(p -> p.getProjectId().equalsIgnoreCase(projectId)).findAny().get();
                    if (project != null) {
                        return project;
                    }
                }
            }
        }
        return null;
    }


    @Override
    public List<Project> getAllProject(String emailId) {
        if (employeeDataRepository.findById(emailId).isPresent()) {
            EmployeeData data = employeeDataRepository.findById(emailId).get();
            List<Project> p1 = data.getProjects();
            System.out.println(p1);
            return p1;
        } else return null;
    }


    @Override
    public Boolean deleteProjectById(String userId, String projectId) throws ProjectNotFoundException,EmployeeNotFoundException{
        if(employeeDataRepository.findById(userId).isPresent()){
            boolean deleteFlag=false;
            EmployeeData employeeData=employeeDataRepository.findById(userId).get();
            List<Project> projectList=employeeData.getProjects();
            deleteFlag= projectList.removeIf(p->p.getProjectId().equalsIgnoreCase(projectId));
            if(deleteFlag){
                employeeDataRepository.save(employeeData);
                return true;
            }
            throw new ProjectNotFoundException();

        }
        throw  new EmployeeNotFoundException();
    }
    @Override
    public EmployeeData addtask(String emailId, String projectId, String columnId, TaskData taskData) throws ProjectNotFoundException,ColumnNotFoundException,EmployeeNotFoundException ,TasklimitExceed ,TaskIdAlreadyExistsException{
        if (employeeDataRepository.findById(emailId).isPresent()) {
            EmployeeData employeeData = employeeDataRepository.findById(emailId).get();
            List<Project> p1 = employeeData.getProjects();
            if (p1.stream().anyMatch(e -> e.getProjectId().equalsIgnoreCase(projectId))) {
                Project p = p1.stream().filter(pr -> pr.getProjectId().equals(projectId)).findAny().get();
                List<Column> columnList = p.getColumnList();
                if (columnList.stream().anyMatch(e1 -> e1.getColumnId().equalsIgnoreCase(columnId))) {
                    Column col = columnList.stream().filter(c -> c.getColumnId().equals(columnId)).findAny().get();
                    List<TaskData> taskDataList = col.getTaskList();
                    int limit = 0;
                    for (int i = 0; i < taskDataList.size(); i++) {
                        if (taskData.getAssigne().equalsIgnoreCase(taskDataList.get(i).getAssigne())) {
                            limit++;
                        }
                    }
                    if (taskDataList.stream().anyMatch(t1 -> t1.getTaskId().equalsIgnoreCase(taskData.getTaskId()))) {
                        throw new TaskIdAlreadyExistsException();
                    } else if (limit > 2) {
                        throw new TasklimitExceed();
                    } else {
                        taskDataList.add(taskData);
                        String assigne = taskData.getAssigne();
                        List<EmployeeData> employeeData1 = employeeDataRepository.findAll();
                        if (employeeData1.stream().anyMatch(a -> a.getEmployeeName().equalsIgnoreCase(assigne))) {
                            EmployeeData emp = employeeData1.stream().filter(i -> i.getEmployeeName().equalsIgnoreCase(assigne)).findAny().get();
                            EmailDetails emailDetails = new EmailDetails();
                            emailDetails.setRecipient(emp.getEmailId());
                            emailDetails.setSubject("You have been Assigned with a Task");
                            emailDetails.setMsgBody(p.toString());
                            producer.sendDTOtoQueue(emailDetails);
                            return employeeDataRepository.save(employeeData);
                        } else {
                            throw new EmployeeNotFoundException();
                        }
                    }
                } else{
                    throw new ColumnNotFoundException();
                }
            }
            else {
                throw new ProjectNotFoundException();
            }
        }else {
            throw new EmployeeNotFoundException();
        }
    }

    @Override
    public EmployeeData updateTask(String emailid,String projectId,String colId,TaskData taskData){
        if (employeeDataRepository.findById(emailid).isPresent()) {
            EmployeeData employeeData = employeeDataRepository.findById(emailid).get();
            List<Project> p1 = employeeData.getProjects();
            Project p = p1.stream().filter(pr -> pr.getProjectId().equals(projectId)).findAny().get();
            List<Column> columnList = p.getColumnList();
            Column col = columnList.stream().filter(c -> c.getColumnId().equals(colId)).findAny().get();
            List<TaskData> taskDataList = col.getTaskList();
            for(int i=0; i<taskDataList.size();i++){
                if(taskDataList.get(i).getTaskId().equalsIgnoreCase(taskData.getTaskId())){
                    taskDataList.set((i),taskData);
                    employeeDataRepository.save(employeeData);
                    return employeeData;
                }
            }
//            TaskData taskData1= taskDataList.stream().filter(c->c.getTaskId().equalsIgnoreCase(taskData.getTaskId())).findAny().get();
//             if(taskData1!=null){
//                 taskData1.setTaskId(taskData.getTaskId());
//                 taskData1.setPriority(taskData.getPriority());
//                 taskData1.setStatus(taskData.getStatus());
//                 taskData1.setTitle(taskData.getTitle());
//                 taskData1.setAssigne(taskData.getAssigne());
//                 taskData1.setDeadline(taskData.getDeadline());
//
//
//
//                 return employeeData;
//             }

        }
        return null;

    }

    public  boolean deleteTask(String emailid,String projectId,String colId,String taskId){
        if (employeeDataRepository.findById(emailid).isPresent()) {
            EmployeeData employeeData = employeeDataRepository.findById(emailid).get();
            List<Project> p1 = employeeData.getProjects();
            Project p = p1.stream().filter(pr -> pr.getProjectId().equals(projectId)).findAny().get();
            List<Column> columnList = p.getColumnList();
            Column col = columnList.stream().filter(c -> c.getColumnId().equals(colId)).findAny().get();
            List<TaskData> taskDataList = col.getTaskList();
            if(taskDataList!=null){
//                employeeDataRepository.deleteById(taskData.getTaskId());
                TaskData task=taskDataList.stream().filter(i->i.getTaskId().equalsIgnoreCase(taskId)).findAny().get();
                taskDataList.remove(task);
                employeeDataRepository.save(employeeData);
                return true;
            }

        }
        return  false;
    }
    @Override
    public List<Column> addColumn(String emailId, String projectId, Column column) throws ColumnAlreadyExistsException, ProjectNotFoundException {
        Column col1 = new Column(column.getColumnId(), new ArrayList<TaskData>());
        EmployeeData emp = employeeDataRepository.findById(emailId).get();
        List<Project> projectList1 = emp.getProjects();
        if (projectList1.stream().anyMatch(p -> p.getProjectId().equalsIgnoreCase(projectId))) {
            Project project1 = projectList1.stream().filter(i -> i.getProjectId().equalsIgnoreCase(projectId)).findAny().get();
            if (project1.getColumnList().stream().filter(c -> c.getColumnId().equalsIgnoreCase(col1.getColumnId())).findAny().isPresent()) {
                throw new ColumnAlreadyExistsException();
            } else {
                project1.getColumnList().add(col1);
                employeeDataRepository.save(emp);
                return project1.getColumnList();
            }
        } else {
            throw new ProjectNotFoundException();
        }

    }

    @Override
    public List<Column> getColumn(String emailId, String projecctId) throws ProjectNotFoundException, EmployeeNotFoundException {
        if (employeeDataRepository.findById(emailId).isPresent()) {
            EmployeeData employeeData = employeeDataRepository.findById(emailId).get();
            List<Project> p = employeeData.getProjects();
            if (p.stream().anyMatch(p2 -> p2.getProjectId().equalsIgnoreCase(projecctId))) {
                Project project = p.stream().filter(p1 -> p1.getProjectId().equals(projecctId)).findAny().get();
                List<Column> columnList = project.getColumnList();
                return columnList;
            } else {
                throw new ProjectNotFoundException();
            }
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    // delete column
    @Override
    public EmployeeData deleteColumn(String emailId, String projectId, String columnId) throws ColumnNotFoundException, ProjectNotFoundException, EmployeeNotFoundException {
        if (employeeDataRepository.findById(emailId).isPresent()) {
            EmployeeData employeeData = employeeDataRepository.findById(emailId).get();
            List<Project> projectList = employeeData.getProjects();
            if (projectList.stream().anyMatch(p3 -> p3.getProjectId().equalsIgnoreCase(projectId))) {
                Project matchingObject = projectList.stream().
                        filter(p -> p.getProjectId().equals(projectId)).findAny().get();
                List<Column> columnList = matchingObject.getColumnList();
                if (columnList.stream().anyMatch(c1 -> c1.getColumnId().equalsIgnoreCase(columnId))) {
                    columnList.removeIf(c -> c.getColumnId().equals(columnId));
                    matchingObject.setColumnList(columnList);
                    return employeeDataRepository.save(employeeData);
                } else {
                    throw new ColumnNotFoundException();
                }
            } else {
                throw new ProjectNotFoundException();
            }
        }
        throw new EmployeeNotFoundException();
    }


    //  project Manager


    // team member


    @Override
    public EmployeeData getTeamMemberProject(String projectid) {
        List<EmployeeData> emplData = employeeDataRepository.findAll();
        for (int i = 0; i < emplData.size(); i++) {
            if (emplData.get(i).getProjects() != null) {
                List<Project> projectsdata = emplData.get(i).getProjects();
                for (int j = 0; j < projectsdata.size(); j++) {
                    if (projectsdata.get(j).getProjectId().equals(projectid)) {
                        return emplData.get(i);
                    }

                }

            }
        }
        return null;
    }


//    // get task By Id
//    public TaskData getTaskById(String taskId) {
//        List<EmployeeData> emplData = employeeDataRepository.findAll();
//        for (int i = 0; i < emplData.size(); i++) {
//            if (emplData.get(i).getProjects() != null) {
//                List<Project> projectsdata = emplData.get(i).getProjects();
//                for (int j = 0; j < projectsdata.size(); j++) {
//                    if (projectsdata.get(j).getColumnList() != null) {
//                        List<Column> columns = projectsdata.get(j).getColumnList();
//                        for (int k = 0; k < columns.size(); k++) {
//                            if (columns.get(k).getTaskList() != null) {
//                                List<TaskData> taskDataList = columns.get(k).getTaskList();
//                                for (int l = 0; l < taskDataList.size(); l++) {
//                                    if (taskDataList.get(l).getTaskId().equals(taskId)) {
//                                        TaskData data = taskDataList.get(l);
//                                        return data;
//                                    }
//                                }
//
//                            }
//                        }
//                    }
//
//                }
//            }
//
//        }
//        return null;
//    }
//
//}


//
//    @Override
//    public boolean deleteTaskByName(String employeeName,TaskData taskData) throws EmployeeNotFoundException{
//        if(employeeDataRepository.findById(employeeName).isPresent()){
//          EmployeeData employeeData =  employeeDataRepository.findById(employeeName).get();
//           employeeData.getTaskDataList().remove(taskData);
//
//            return true;
//        }
//       throw new EmployeeNotFoundException();
//    }
//
//    @Override
//    public EmployeeData upateTask(String employeeName, TaskData taskData) throws EmployeeNotFoundException {
//        if(employeeDataRepository.findById(employeeName).isPresent()){
//           EmployeeData emp = employeeDataRepository.findById(employeeName).get();
//           emp.getTaskDataList().add(taskData);
//            return employeeDataRepository.save(emp);
//        }
//        throw new EmployeeNotFoundException();
//    }

}