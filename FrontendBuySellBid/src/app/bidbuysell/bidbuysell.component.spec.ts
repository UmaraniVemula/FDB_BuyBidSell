import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BidbuysellComponent } from './bidbuysell.component';

describe('BidbuysellComponent', () => {
  let component: BidbuysellComponent;
  let fixture: ComponentFixture<BidbuysellComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BidbuysellComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BidbuysellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
