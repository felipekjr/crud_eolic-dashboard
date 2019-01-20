import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

import {ParqueService} from '../../_services/parque.service'
import {Parque} from '../../_models/parque'

@Component({
  selector: 'app-parque-eolico',
  templateUrl: './parque-eolico.component.html',
  styleUrls: ['./parque-eolico.component.scss']
})
export class ParqueEolicoComponent implements OnInit {
  parques: Parque[];
  @Input() hasComplexo: boolean;
  @Output() hasParque = new EventEmitter<boolean>();
  constructor(private parqueService: ParqueService) { }


  deleteParque(parque : Parque){    
    this.parqueService.deleteParque(parque.id)
      .subscribe(data => {       
        this.parqueService.getParques()
        .subscribe( data => {
          if(data){          
            this.hasParque.emit(true);
          }else{
            this.hasParque.emit(false);
          }
          this.parques = data;
        });        
      })      
  }


  ngOnInit() {
    this.parqueService.getParques()
      .subscribe( data => {
        if(data){          
          this.hasParque.emit(true);
        }
        this.parques = data;
      });
  }

}
