import { Component, OnInit,Output, EventEmitter } from '@angular/core';

import { ComplexoService } from '../../_services/complexo.service'

import { Complexo } from '../../_models/complexo'

@Component({
  selector: 'app-complexo-eolico',
  templateUrl: './complexo-eolico.component.html',
  styleUrls: ['./complexo-eolico.component.scss']
})
export class ComplexoEolicoComponent implements OnInit {
  complexos: Complexo[]; 
  @Output() hasComplexo = new EventEmitter<boolean>();
  constructor(private complexoService: ComplexoService) { }

  ngOnInit() {
    this.complexoService.getComplexos()
      .subscribe( data => {
        if(data){
          this.hasComplexo.emit(true);           
        }
        this.complexos = data;
      });
      
  }

}
