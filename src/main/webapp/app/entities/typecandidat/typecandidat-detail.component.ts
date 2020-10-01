import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypecandidat } from 'app/shared/model/typecandidat.model';

@Component({
  selector: 'jhi-typecandidat-detail',
  templateUrl: './typecandidat-detail.component.html',
})
export class TypecandidatDetailComponent implements OnInit {
  typecandidat: ITypecandidat | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typecandidat }) => (this.typecandidat = typecandidat));
  }

  previousState(): void {
    window.history.back();
  }
}
