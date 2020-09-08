import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INiveauInstruction } from 'app/shared/model/niveau-instruction.model';

@Component({
  selector: 'jhi-niveau-instruction-detail',
  templateUrl: './niveau-instruction-detail.component.html',
})
export class NiveauInstructionDetailComponent implements OnInit {
  niveauInstruction: INiveauInstruction | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ niveauInstruction }) => (this.niveauInstruction = niveauInstruction));
  }

  previousState(): void {
    window.history.back();
  }
}
