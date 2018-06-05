import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RulesDefinitionComponent } from './rules-definition.component';

describe('RulesDefinitionComponent', () => {
  let component: RulesDefinitionComponent;
  let fixture: ComponentFixture<RulesDefinitionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RulesDefinitionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RulesDefinitionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
