import { ComponentFixture, TestBed } from '@angular/core/testing';

import { KanabanHomeComponent } from './kanaban-home.component';

describe('KanabanHomeComponent', () => {
  let component: KanabanHomeComponent;
  let fixture: ComponentFixture<KanabanHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ KanabanHomeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(KanabanHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
