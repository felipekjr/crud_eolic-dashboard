import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  hasComplexo : boolean;
  hasParque : boolean
  constructor() { }


  onMatchComplexo(event : boolean){
    event ? this.hasComplexo = true : this.hasComplexo = false;    
  }
  onMatchParque(event : boolean){
    event ? this.hasParque = true : this.hasParque = false;    
  }

  ngOnInit() {
  }

}
