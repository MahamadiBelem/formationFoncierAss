import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFormations } from 'app/shared/model/formations.model';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'jhi-formations-detail',
  templateUrl: './formations-detail.component.html',
})
export class FormationsDetailComponent implements OnInit {
  @Input() public formations: any;

  constructor(protected activatedRoute: ActivatedRoute, private activemodale: NgbActiveModal) {}

  ngOnInit(): void {
    //this.activatedRoute.data.subscribe(({ formations }) => (this.formations = formations));
  }

  previousState(): void {
    window.history.back();
  }

  cancel(): void {
    this.activemodale.dismiss();
  }
}
