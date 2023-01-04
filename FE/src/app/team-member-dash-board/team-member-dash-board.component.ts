import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { Component, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TeamService } from '../team.service';
import { ViewTaskDialogComponent } from '../view-task-dialog/view-task-dialog.component';

@Component({
  selector: 'app-team-member-dash-board',
  templateUrl: './team-member-dash-board.component.html',
  styleUrls: ['./team-member-dash-board.component.css']
})
export class TeamMemberDashBoardComponent implements OnInit {

  constructor(private teamMemberService:TeamService,public dialog: MatDialog,private snackbar: MatSnackBar) { }

  ngOnInit(): void {
    this.getProjectManager();
  }

  // @Input() pId:any

  // search the project based on project Id
  pId:any;
  searchingItems(search:any){
    this.pId=search;
    this.searchProject();
    
  }

  


project1:any;
getcolumnDetail:any;
  name= localStorage.getItem('name');
 
   connectedTo:any[]=[];
    projectName1:any;


   searchProject(){
     console.log(this.pId)
     
    this.teamMemberService.getTeamMemberProject(this.pId).subscribe(
      response=>{
        
        this.project1=response
          this.getcolumnDetail=this.project1.columnList;
          this.projectName1=this.project1.projectName;
        console.log(response);

        console.log(this.project1);

        console.log(this.getcolumnDetail);
        
        this.getProjectManager();
        for(let dropColumn of this.getcolumnDetail){
          this.connectedTo.push(dropColumn.columnId)
        }
      
        
      }
    )
  }

  projectManager:any;
  projectManagerName:any;
  projectManagerMailId:any

  getProjectManager(){
  console.log(this.pId)
    this.teamMemberService.getProjectManagerMailId(this.pId).subscribe(
      response=>{
        console.log(response);
        this.projectManager=response;
        this.projectManagerName=this.projectManager.employeeName;
        this.projectManagerMailId=this.projectManager.emailId;
        console.log(this.projectManagerName);
        console.log(this.projectManagerMailId);


      }
    )
   }

   
  drop(event: CdkDragDrop<string[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      this.updateTaskStatusAfterDragDrop(event);
      transferArrayItem(event.previousContainer.data,
                        event.container.data,
                        event.previousIndex,
                        event.currentIndex);
    }
  }
  updateTaskStatusAfterDragDrop(event: CdkDragDrop<string[], string[], any>) {
  let taskId = event.item.element.nativeElement.id;
  let currentcolumn=event.container.id;
  let previouscolumn=event.previousContainer.id;
  this.teamMemberService.getTaskById(this.projectManagerMailId,this.pId,previouscolumn,taskId).subscribe(
    response=>{
      let task=response;
      this.updateTaskColumn(currentcolumn,previouscolumn,task,);
    }
    );
  }
   updateTaskColumn(currentcolumn:any,previouscolumn:any,task:any){
    this.teamMemberService.updateTaskColumn(this.projectManagerMailId,this.pId,currentcolumn,previouscolumn,task).subscribe(response=>{
      // alert("updated task column");
      this.snackbar.open("task is Updated", "close")
      console.log(response);
    });
   }
 
    
   shareTask:any;
   viewTask(task:any){
     console.log(task);
     this.shareTask=task;
 
     
     const dialogRef = this.dialog.open(ViewTaskDialogComponent,{
       data:{
         sharetask:this.shareTask
       }
     });
     
 
     dialogRef.afterClosed().subscribe(result => {
       console.log(`Dialog result: ${result}`);
      
     });
    
   } 


}
