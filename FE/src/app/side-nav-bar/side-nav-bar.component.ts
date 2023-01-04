import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { CreateKanbanComponent } from '../create-kanban/create-kanban.component';
import { KanabanHomeComponent } from '../kanaban-home/kanaban-home.component';
import { ShareDataService } from '../share-data.service';

import { TaskServiceService } from '../task-service.service';

@Component({
  selector: 'app-side-nav-bar',
  templateUrl: './side-nav-bar.component.html',
  styleUrls: ['./side-nav-bar.component.css']
})
export class SideNavBarComponent implements OnInit {

  constructor(public dialog: MatDialog,private taskService:TaskServiceService, private router :Router,private shareDataservice:ShareDataService,private kanban:KanabanHomeComponent) { 
    this.getAllProjectName();
  }

  ngOnInit(): void {
  }


  createKanban(){
    const dialogRef = this.dialog.open(CreateKanbanComponent,{
      // height: '60%',
      // width: '50%'
    })
   

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
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
       console.log(this.project)
       
       
     }
   )
   
  }
   projectids:any;
   dispayProject:any;
  // select the project from list 
  selectProject(pro:any){
    console.log(pro)
    this.dispayProject=pro;
    console.log(this.dispayProject);
    
     localStorage.setItem('projectid',this.dispayProject)
            
      //  this.kanban.getData();
      //  this.kanban.getprojectById;
      //  this.kanban.getColumn();
       
       this.router.navigateByUrl("/sidenav/kanban")
       window.location.reload();
   
    }

  }

