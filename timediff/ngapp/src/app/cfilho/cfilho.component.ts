import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-cfilho',
  templateUrl: './cfilho.component.html',
  styleUrls: ['./cfilho.component.css']
})
export class CfilhoComponent implements OnInit {

  @Input() paramFilho: string;


  constructor() { }

  ngOnInit() {
    console.log(this.paramFilho);
  }

  public show() {
    alert(this.paramFilho);
  }

}
