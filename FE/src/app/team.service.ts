import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TeamService {

 
  constructor(private httpcilent:HttpClient) { }

  baseUrl="http://localhost:8888/employee-details/v1";
  //("/team-member-task-list/{projectId}/{assigne}")
  
   projectId:any;

   viewdata:any;

   // get column from Project member
  getTeamMemberProject(projectid:any){
    return this.httpcilent.get(this.baseUrl+"/team-member-project/"+projectid+"/"+localStorage.getItem('name'));

  }
   

  //  /add-project-to-team-member/{emailId}")
  addProjectToTeamMember(project:any){
    return this.httpcilent.post(this.baseUrl+"/add-project-to-team-member/"+localStorage.getItem('email')+"/",project);
  }


 
 
 //get-task-by-Id/{emailId}/{projectId}/{columnId}/{taskId}"
  getTaskById(projectManagerId:any,projectids:any,previouscolumn:any,taskId:any){
    return this.httpcilent.get(this.baseUrl+"/get-task-by-Id/"+projectManagerId+"/"+projectids+"/"+previouscolumn+"/"+taskId);

  }


    // ("/update-column/{emailId}/{projectId}/{currentColumn}/{previousColumn}"
    updateTaskColumn(projectManagerId:any,projectId:any,currentColumn:any,previousColumn:any,task:any){
      return this.httpcilent.put(this.baseUrl+"/update-column/"+projectManagerId+"/"+projectId+"/"+currentColumn+"/"+previousColumn,task);
    }
  


    //get-project-manager-emailid/{projectId}")
    getProjectManagerMailId(pid:any){
      return this.httpcilent.get(this.baseUrl+"/get-project-manager-emailid/"+pid);
    }
}
