import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamMemberDashBoardComponent } from './team-member-dash-board.component';

describe('TeamMemberDashBoardComponent', () => {
  let component: TeamMemberDashBoardComponent;
  let fixture: ComponentFixture<TeamMemberDashBoardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeamMemberDashBoardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeamMemberDashBoardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
