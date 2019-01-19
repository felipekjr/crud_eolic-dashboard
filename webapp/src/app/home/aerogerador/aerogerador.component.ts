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
  @Input() hasParque : boolean;
  constructor(private aerogeradorService: AerogeradorService) { }

  ngOnInit() {
    this.aerogeradorService.getAerogeradores()
      .subscribe( data => {
        this.aerogeradores = data;
      });
  }

}
