import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { TaskServiceService } from '../task-service.service';

@Component({
  selector: 'app-sub-task-dilog-box',
  templateUrl: './sub-task-dilog-box.component.html',
  styleUrls: ['./sub-task-dilog-box.component.css']
})
export class SubTaskDilogBoxComponent implements OnInit {
  taskError: any;

  constructor(private taskService:TaskServiceService, private snackbar:MatSnackBar, private router:Router) { }

  ngOnInit(): void {
  }


  
  taskForm = new FormGroup({
    taskId: new FormControl('',[Validators.required]),
    title: new FormControl('',[Validators.required]),
    assigne: new FormControl('',[Validators.required]),
    deadline: new FormControl('',[Validators.required]),
    priority: new FormControl('',[Validators.required]),
    status:new FormControl('',[Validators.required])
  })

     
sendtask(){
  this.taskService.sendTask(this.taskForm.value).subscribe(
    response=>{
      console.log(response);
      this.snackbar.open("task is added", "close")
      // window.location.reload();
      this.taskForm.reset();
      //  this.router.navigateByUrl('/kanban')
      // this.selectProject(this.project,this.projectids);
      // this.isSubTask=false;
      // this.getAllTask();
      // this.issubtaskEnable=true;
    },
    error=>{
      this.taskError=error;
      this.snackbar.open(this.taskError.error.message,"close");
      this.taskForm.reset();
     }
  )
 }
}
