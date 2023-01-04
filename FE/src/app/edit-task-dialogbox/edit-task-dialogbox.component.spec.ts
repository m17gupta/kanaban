import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditTAskDialogboxComponent } from './edit-task-dialogbox.component';

describe('EditTAskDialogboxComponent', () => {
  let component: EditTAskDialogboxComponent;
  let fixture: ComponentFixture<EditTAskDialogboxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditTAskDialogboxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditTAskDialogboxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
