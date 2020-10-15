import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cpai',
  templateUrl: './cpai.component.html',
  styleUrls: ['./cpai.component.css']
})
export class CpaiComponent implements OnInit {

  varPai: string = 'VARIAVEL DO PAI';

  constructor() { }

  ngOnInit() {
  }

}
