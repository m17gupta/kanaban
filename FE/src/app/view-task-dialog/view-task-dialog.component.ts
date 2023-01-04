import { Component, Inject, Input, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { TaskServiceService } from '../task-service.service';

@Component({
  selector: 'app-view-task-dialog',
  templateUrl: './view-task-dialog.component.html',
  styleUrls: ['./view-task-dialog.component.css']
})
export class ViewTaskDialogComponent implements OnInit {

  constructor(@Inject(MAT_DIALOG_DATA) public data:any ) {   }

  ngOnInit(): void {
    this.viewTask=this.data.sharetask
    console.log((this.data.sharetask));
      this.viewTaskForm.setValue(this.viewTask)
  }


  viewTaskForm = new FormGroup({
    assigne: new FormControl(''),
    deadline: new FormControl(''),
    priority: new FormControl(''),
    status:new FormControl(''),
    taskId: new FormControl(''),
    title: new FormControl(''),
  
    
  })

  viewTask:any;
  // getdata(){
  //  this.viewTask=this.data.sharetask
  //  console.log((this.data.sharetask));
  //    this.viewTaskForm.setValue(this.viewTask)
  // }
   

}
