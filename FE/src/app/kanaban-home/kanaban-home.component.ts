
import { CdkDragDrop, moveItemInArray, transferArrayItem } from '@angular/cdk/drag-drop';
import { Component, OnInit } from '@angular/core';

import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ColumnDialogBoxComponent } from '../column-dialog-box/column-dialog-box.component';
import { ColumnServiceService } from '../column-service.service';
import { CreateKanbanComponent } from '../create-kanban/create-kanban.component';
import { EditTAskDialogboxComponent } from '../edit-task-dialogbox/edit-task-dialogbox.component';
import { ShareDataService } from '../share-data.service';
import { SubTaskDilogBoxComponent } from '../sub-task-dilog-box/sub-task-dilog-box.component';
import { TaskServiceService } from '../task-service.service';
import { ViewTaskDialogComponent } from '../view-task-dialog/view-task-dialog.component';




@Component({
  selector: 'app-kanaban-home',
  templateUrl: './kanaban-home.component.html',
  styleUrls: ['./kanaban-home.component.css']
})


export class KanabanHomeComponent implements OnInit {

  constructor(private columnService:ColumnServiceService ,  public dialog: MatDialog,
      private taskService:TaskServiceService,private snackbar: MatSnackBar) {
   this.getAllProjectName();
  //  if(localStorage.getItem('projectid')!=null){
  //   console.log(localStorage.getItem('projectid'))
  //   this.dispayProject=true;
  //   this.getColumn();
  //   this.pname=localStorage.getItem('projectname');
  //   this.dur=localStorage.getItem('duration')
  //  }
  
    
   }

  ngOnInit(): void {
   
  }

  
 pname:any;
 dur:any;
  createKanban(){
    const dialogRef = this.dialog.open(CreateKanbanComponent,{
      // height: '60%',
      // width: '50%'
    })
   

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
      this.getAllProjectName();
    });
  }

  name= localStorage.getItem('name')
     // get all projects
     projectid:any;
  project:any;

  getAllProjectName(){
   this.taskService.getAllProjectName().subscribe(
     response=>{
    
       this.project=response; 
       console.log(this.project);
       console.log('method call')
       
       
     }
   )
   
  }
   projectids:any;
   dispayProject:boolean=false;
   selectedProject:any;
  // select the project from list 
  selectProject(){
    // console.log(pro);
    
    console.log(this.dispayProject);
    this.istableEnable=true;
    
     localStorage.setItem('projectid',this.selectedProject)
     
     this.dispayProject=true;
     this.istableEnable=true; 
     this.getprojectById();
     this.getColumn();
    //  location.reload();
     console.log(this.projectids)
    
    }

      deleteProject(){
        console.log(this.selectedProject);
        var result = window.confirm("Are you sure you want to delete the project");
        if(result){
          this.taskService.deleteProject(this.selectedProject).subscribe(
            response=>{
              console.log(response);
              if(response){
                this.snackbar.open("Project is deleted", "close");
                this.getprojectById();
                location.reload();
              }
            }
          )

        }
       
      }

 
  istableEnable:boolean=false;
  


  projectName1 :any;
  duration1:any;
  projectData1:any;
  getallColumn:any;
  getprojectById(){
    this.taskService.getProjectById().subscribe(
      responce=>{
        console.log(responce)
        this.projectData1=responce;
        console.log(this.projectData1);
        this.projectName1=this.projectData1.projectName;
        localStorage.setItem('projectname', this.projectName1)
        this.duration1=this.projectData1.duration
        localStorage.setItem('duration',this.duration1)
        this.getColumn();
      }
    )
  }

  // column

  

  isColumnEnable:boolean=false;
  isSelected:boolean=false;
  isProjectSelected:boolean=false;
  

  // enable /disbale the column form
  isColumnDisable:boolean=false;

      addColumnMethod(){
        this.isColumnDisable=false;
         }


        add(){
          const dialogRef = this.dialog.open(ColumnDialogBoxComponent,{
        
          });

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
      this.getColumn();
    });
        }

        isProjectDisplay:boolean=false;
     getFirstColId:any;
     getcolumnDetail:any;
     getColumn(){
      
      this.istableEnable=true;
      this.columnService.getColumn().subscribe(
        response=>{
          console.log(response)
          this.getcolumnDetail=response;
         
          console.log(this.getcolumnDetail);
          this.getFirstColId=this.getcolumnDetail[0].columnId;
          console.log(this.getFirstColId)
          
        }
      )
      
     }
  
     deleteColumn(id:any){
      console.log(id);
      var delCol=window.confirm("Are you sure to delete the column");
      if(delCol){
        this.columnService.deleteColumn(id).subscribe(
          response=>{
            console.log(response);
            this.getColumn();
          })
      }

      


     }
    
      
  todo :any;
  inpro:any []=[];
  done :any []= [];

  drop(event: CdkDragDrop<String[]>) {
    if (event.previousContainer === event.container) {
      moveItemInArray(event.container.data, event.previousIndex, event.currentIndex);
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex,
      );
    }
  }

 

  subTask(){
    const dialogRef = this.dialog.open(SubTaskDilogBoxComponent);
    

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
      
      this.getprojectById();
     
    });
    localStorage.setItem('firstColId',this.getFirstColId);
   
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
  
    edittask:any;
  editTask(edit:any){
    this.edittask=edit;
    const dialogRef = this.dialog.open(EditTAskDialogboxComponent,{
      data:{
        sharedata:this.edittask
      }
    });
    
     

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
      this.getColumn();
     
    });
    // this.viewtaskComponent.getdata();
   

  }

  
  deleteTask(item:any){
    var result=window.confirm("Are you sure to delete the task" );
    if(result){
      this.taskService.deleteTask(item).subscribe(
        response=>{
          console.log(response);
          if(response){
            this.snackbar.open("task is deleted", "close");
            // this.gettransfer();
            this.getColumn();
  
          }
        }
      )
    }
    

  }

}
