import { HttpClient,HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TaskServiceService {

  constructor(private httpServer:HttpClient) { }

  baseUrl="http://localhost:8888/employee-details/v1";

     
   viewTAsk:any;

   // get All project NAme for particular Admin
   getAllProjectName(){
    return this.httpServer.get(this.baseUrl+"/get-all-project/"+localStorage.getItem('email'));
   }

  

  // sending  project name detail to db  http://localhost:9999/employee-details/v1/add-project/man1@gmail.com
  sendProject(project:any){
    return this.httpServer.post(this.baseUrl+"/add-project/"+localStorage.getItem('email'),project);
  }
   
    ///get-project-by-Id/{email}/{projectId}")

     getProjectById(){
      return this.httpServer.get(this.baseUrl+"/get-project-by-Id"+"/"+localStorage.getItem('email')+"/"+localStorage.getItem('projectid'))
     }

  //http://localhost:9999/employee-details/v1/task-list/man1@gmail.com/PN001
  getTask(projectId1:any){
    return this.httpServer.get(this.baseUrl+"/task-list/"+localStorage.getItem('email')+"/"+projectId1);
  }


    ///add-task/{emailid/projectId/column Id }
  //sending sub task to DB
  projectId:any;
  colIds:any;

  shareDataToAddTask(projId:any,colId:any){
   this.projectId=projId;
   this.colIds=colId;

  }

    sendTask(subTask:any){
      return this.httpServer.post(this.baseUrl+"/add-task/"+localStorage.getItem('email')+
      "/"+localStorage.getItem('projectid')+"/"+localStorage.getItem('firstColId'),subTask);

    }

   updateTask(task:any){
    return this.httpServer.put(this.baseUrl+"/update-task/"+localStorage.getItem('email')+
    "/"+localStorage.getItem('projectid')+"/"+localStorage.getItem('firstColId'),task)

   }

   deleteTask(task1:any)
   {
    return this.httpServer.delete(this.baseUrl+"/delete-task/"+localStorage.getItem('email')+
    "/"+localStorage.getItem('projectid')+"/"+localStorage.getItem('firstColId')+"/"+task1)

}

   deleteProject( projectId:any){
    return this.httpServer.delete(this.baseUrl+"/delete-project/"+localStorage.getItem('email')+
    "/"+projectId)

   }
}
