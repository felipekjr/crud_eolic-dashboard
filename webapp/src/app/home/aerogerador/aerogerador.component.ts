import { Component, OnInit, Input } from '@angular/core';

import {AerogeradorService} from '../../_services/aerogerador.service'
import {Aerogerador} from '../../_models/aerogerador'

@Component({
  selector: 'app-aerogerador',
  templateUrl: './aerogerador.component.html',
  styleUrls: ['./aerogerador.component.scss']
})
export class AerogeradorComponent implements OnInit {
  aerogeradores : Aerogerador[]; 
  aerogeradorForUpdate: Aerogerador;
  @Input() hasParque : boolean;
  constructor(private aerogeradorService: AerogeradorService) { }


  editAerogerador(aerogerador: Aerogerador){
    if(aerogerador){
      this.aerogeradorForUpdate = aerogerador;
    }else{
      this.aerogeradorForUpdate = null;
    }    
  }

  deleteAerogerador(aerogerador : Aerogerador){    
    this.aerogeradorService.deleteAerogerador(aerogerador.id)
      .subscribe(data => {       
          this.aerogeradorService.getAerogeradores()
            .subscribe( data => {
              this.aerogeradores = data;
            });        
      })      
  }

  ngOnInit() {
    this.aerogeradorService.getAerogeradores()
      .subscribe( data => {
        this.aerogeradores = data;
      });
  }

}
