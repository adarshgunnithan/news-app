import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LocalnewslistComponent } from './localnewslist.component';

describe('LocalnewslistComponent', () => {
  let component: LocalnewslistComponent;
  let fixture: ComponentFixture<LocalnewslistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LocalnewslistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LocalnewslistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
