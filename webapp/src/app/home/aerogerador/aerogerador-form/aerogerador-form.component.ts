import { Component, Input, SimpleChanges, OnChanges } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Aerogerador } from '../../../_models/aerogerador'

@Component({
  selector: 'app-aerogerador-form',
  templateUrl: './aerogerador-form.component.html',
  styleUrls: ['./aerogerador-form.component.scss']
})
export class AerogeradorFormComponent implements OnChanges {
  aerogeradorForm: FormGroup
  @Input() aerogerador
  constructor(private formBuilder: FormBuilder) { }  
  
  ngOnChanges(changes: SimpleChanges){
    if(this.aerogerador!=null){
      this.aerogeradorForm = this.formBuilder.group({
        nome: [this.aerogerador.nome, Validators.required],
        latitude: [this.aerogerador.latitude],
        longitude: [this.aerogerador.longitude],
        altura_torre: [this.aerogerador.altura_torre],
        diametro_varredura: [this.aerogerador.diametro_varredura],
        modelo: [this.aerogerador.modelo],
        parque_eolico_id: [this.aerogerador.parque_eolico_id, Validators.required],
      })
    }else{
      this.aerogeradorForm = this.formBuilder.group({
        nome: ['', Validators.required],
        latitude: ['',],
        longitude: ['',],
        altura_torre: ['',],
        diametro_varredura: ['',],
        modelo: ['',],
        parque_eolico_id: ['', Validators.required],
      })
    }
  }
  
  
}
