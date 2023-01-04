import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { TaskServiceService } from '../task-service.service';

@Component({
  selector: 'app-create-kanban',
  templateUrl: './create-kanban.component.html',
  styleUrls: ['./create-kanban.component.css']
})
export class CreateKanbanComponent implements OnInit {

  constructor( private taskService:TaskServiceService, private router:Router,private snackbar: MatSnackBar) { }

  ngOnInit(): void {
  }
  
  projectForm=new FormGroup({
    projectId: new FormControl('',[Validators.required]),    
    projectName: new FormControl('',[Validators.required]),
    duration: new FormControl('',[Validators.required])
   
    })
  



 

  isDialogEnable:boolean=true;
  name:any;

  id=this.projectForm.value.projectId;


  project(){
  this.taskService.sendProject(this.projectForm.value).subscribe(
    response=>{
      console.log(response);
      
      this.name=this.projectForm.value.projectName;
        console.log(this.projectForm.value.projectId);
       
       
      console.log(this.id);
      
     // alert("project is add on kanban board")
      this.snackbar.open("project is add on kanban board", "close")
      // window.location.reload();
    }
  )

  }


}
