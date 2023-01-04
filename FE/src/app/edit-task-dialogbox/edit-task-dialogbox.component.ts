import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { TaskServiceService } from '../task-service.service';

@Component({
  selector: 'app-edit-task-dialogbox',
  templateUrl: './edit-task-dialogbox.component.html',
  styleUrls: ['./edit-task-dialogbox.component.css']
})
export class EditTAskDialogboxComponent implements OnInit {
  taskError: any;

  constructor( @Inject(MAT_DIALOG_DATA) public data:any, private taskService:TaskServiceService,
  private snackbar:MatSnackBar) { this.editForm()}

  ngOnInit(): void {
  }
  
  
 editTaskForm = new FormGroup({
    taskId: new FormControl(''),
    title: new FormControl(''),
    assigne: new FormControl(''),
    deadline: new FormControl(''),
    priority: new FormControl(''),
    status:new FormControl('')
  })

  editForm(){
    this.editTaskForm.setValue(this.data.sharedata)
  }
     
sendtask(){
  this.taskService.updateTask(this.editTaskForm.value).subscribe(
    response=>{
      console.log(response);
      // window.location.reload();
      this.snackbar.open("task is updated", "close")
      this.editTaskForm.reset();
      
      // this.selectProject(this.project,this.projectids);
      // this.isSubTask=false;
      // this.getAllTask();
      // this.issubtaskEnable=true;
    },
    error=>{
      this.taskError=error;
      this.snackbar.open(this.taskError.error.message,"close");
      this.editTaskForm.reset();
     }
  )
 }


}
