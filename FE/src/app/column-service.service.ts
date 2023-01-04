import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ColumnServiceService {

  constructor( private httcilent:HttpClient) { }


     

    baseUrl="http://localhost:8888/employee-details/v1"


  //http://localhost:9999/employee-details/v1/add-column/ramu@gmail.com/PM001

    addColumn(column:any){
      
      return this.httcilent.post(this.baseUrl+"/add-column/"+localStorage.getItem('email')+"/"+localStorage.getItem('projectid'),column);
    }


   // http://localhost:9999/employee-details/v1/get-all-columns/mohityadav@gmail.com/PM001

    getColumn(){
      return this.httcilent.get(this.baseUrl+"/get-column/"+localStorage.getItem('email')+"/"+localStorage.getItem('projectid'));
    }


    // http://localhost:9999/employee-details/v1/delete-column/{emailId}/{projectId}/{columnId}
    deleteColumn(columnId:any){
      return this.httcilent.delete(this.baseUrl+"/delete-column/"+localStorage.getItem('email')+"/"+localStorage.getItem('projectid')+"/"+columnId)
    }

}
