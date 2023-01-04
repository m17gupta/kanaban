import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamSideNavBarComponent } from './team-side-nav-bar.component';

describe('TeamSideNavBarComponent', () => {
  let component: TeamSideNavBarComponent;
  let fixture: ComponentFixture<TeamSideNavBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeamSideNavBarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TeamSideNavBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
