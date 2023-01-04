import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HomeComponent } from './home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {MatCardModule} from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatListModule } from '@angular/material/list';
import { MatSelectModule } from '@angular/material/select';
import { HttpClientModule } from '@angular/common/http';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatDialogModule, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatTooltipModule } from '@angular/material/tooltip';
import {MatTableModule} from '@angular/material/table';
import {MatRadioModule} from '@angular/material/radio';
import { DragDropModule } from '@angular/cdk/drag-drop';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatGridListModule} from '@angular/material/grid-list';
import { MatMenuModule } from '@angular/material/menu';
import { SignUpDialogComponent } from './sign-up-dialog/sign-up-dialog.component';
import { LoginComponent } from './login/login.component';
import { KanabanHomeComponent } from './kanaban-home/kanaban-home.component';
import {MatSnackBar} from '@angular/material/snack-bar';
import { SideNavBarComponent } from './side-nav-bar/side-nav-bar.component';
import { CreateKanbanComponent } from './create-kanban/create-kanban.component';
import { SubTaskDilogBoxComponent } from './sub-task-dilog-box/sub-task-dilog-box.component';
import { TeamSideNavBarComponent } from './team-side-nav-bar/team-side-nav-bar.component';

import { TeamMemberDashBoardComponent } from './team-member-dash-board/team-member-dash-board.component';
import { ViewTaskDialogComponent } from './view-task-dialog/view-task-dialog.component';
import { ColumnDialogBoxComponent } from './column-dialog-box/column-dialog-box.component';
import { EditTAskDialogboxComponent } from './edit-task-dialogbox/edit-task-dialogbox.component';
import { AboutUsComponent } from './about-us/about-us.component';
import {MatPaginatorModule} from '@angular/material/paginator';


  

 
@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SignUpDialogComponent,
    LoginComponent,
    KanabanHomeComponent,
    SideNavBarComponent,
    CreateKanbanComponent,
    SubTaskDilogBoxComponent,
    TeamSideNavBarComponent,

    TeamMemberDashBoardComponent,
     ViewTaskDialogComponent,
     ColumnDialogBoxComponent,
     EditTAskDialogboxComponent,
     AboutUsComponent,
    
  ],
  imports: [
    BrowserModule,MatCardModule,MatButtonModule,MatToolbarModule ,MatIconModule,MatFormFieldModule, MatInputModule,MatListModule,
    AppRoutingModule,MatSelectModule,HttpClientModule,MatButtonToggleModule,MatDialogModule, MatTooltipModule ,MatSnackBarModule,
    BrowserAnimationsModule,FormsModule,ReactiveFormsModule,MatTableModule,MatRadioModule,DragDropModule,MatSidenavModule,
    MatGridListModule,MatPaginatorModule,
    MatMenuModule,
    
  ],
  providers: [ViewTaskDialogComponent,EditTAskDialogboxComponent,KanabanHomeComponent,
    { provide: MAT_DIALOG_DATA, useValue: {} },
    { provide: MatDialogRef, useValue: {} }],
  bootstrap: [AppComponent],
  
})
export class AppModule { }
