import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ColumnServiceService } from '../column-service.service';
import { KanabanHomeComponent } from '../kanaban-home/kanaban-home.component';

@Component({
  selector: 'app-column-dialog-box',
  templateUrl: './column-dialog-box.component.html',
  styleUrls: ['./column-dialog-box.component.css']
})
export class ColumnDialogBoxComponent implements OnInit {

  constructor( private columnService:ColumnServiceService, private route:Router, private kanban:KanabanHomeComponent,  private snackbar:MatSnackBar) { }
    
  ngOnInit(): void {
  }

   
  
  columnForm1= new FormGroup({
    columnId: new FormControl('',[Validators.required]),
    // columnName: new FormControl('',[Validators.required])
  })

  columnDetail:any;
  colId:any;
  addColumn(){
   this.columnService.addColumn(this.columnForm1.value).subscribe(
     response=>{
       console.log(response);
      this.columnDetail=response;
      this.colId=this.columnDetail.columnId;
      console.log(this.colId);
      //  location.reload();
      this.snackbar.open("Column Added", "close")
    
       this.kanban.getColumn();
      
     //  this.issubtaskEnable=true;
     }
   )
  }

}
