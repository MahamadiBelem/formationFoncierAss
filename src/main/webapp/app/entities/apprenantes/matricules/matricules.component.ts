import { Component, OnInit } from '@angular/core';
import { ApprenantesService } from '../apprenantes.service';

@Component({
  selector: 'jhi-matricules',
  templateUrl: './matricules.component.html',
  styleUrls: ['./matricules.component.scss'],
})
export class MatriculesComponent implements OnInit {
  dataview: any;
  constructor(private matriculeService: ApprenantesService) {}
  ngOnInit(): void {}
}
