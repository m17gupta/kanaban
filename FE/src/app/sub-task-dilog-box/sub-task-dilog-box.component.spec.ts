import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubTaskDilogBoxComponent } from './sub-task-dilog-box.component';

describe('SubTaskDilogBoxComponent', () => {
  let component: SubTaskDilogBoxComponent;
  let fixture: ComponentFixture<SubTaskDilogBoxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubTaskDilogBoxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubTaskDilogBoxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
