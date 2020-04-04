import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-tdiff',
  templateUrl: './app-tdiff.component.html',
  styleUrls: ['./app-tdiff.component.css']
})
export class AppTdiffComponent implements OnInit {

  public ti: string;
  public tf: string;
  public res: string;


  constructor(private http: HttpClient) {
  }

  ngOnInit() {
  }

  getTimeDiff() {
    let params: ParamsCalcDiff = new ParamsCalcDiff();
    params.dateStart = this.ti;
    params.dateStop  = this.tf;

    this.timeDiffService(params).subscribe(
        (result) => {
          console.log(result);

          this.res = result.hours + 'h ' + result.minutes + 'min';
        }
    );
  }

  timeDiffService(body): Observable<any> {
    let url: string = 'http://localhost:8899/timediff/rest/service';

    return this.http.post(url, body);
  }

  calcTimeDiff() {
    let dtPartida = "20000101 " + this.ti;
    let dtChegada = "20000101 " + this.tf;

    let date1: any = new Date(
                  parseInt(dtPartida.slice(0,4)), 
                  parseInt(dtPartida.slice(4,6)),
                  parseInt(dtPartida.slice(6,8)),
                  parseInt(dtPartida.slice(9,11)), 
                  parseInt(dtPartida.slice(12,14))
                );
    let date2: any = new Date(
                  parseInt(dtChegada.slice(0,4)), 
                  parseInt(dtChegada.slice(4,6)),
                  parseInt(dtChegada.slice(6,8)), 
                  parseInt(dtChegada.slice(9,11)), 
                  parseInt(dtChegada.slice(12,14))
                );

    let diffMs   = (date2 - date1);
    let diffHrs  = Math.floor((diffMs % 86400000) / 3600000);
    let diffMins = Math.round(((diffMs % 86400000) % 3600000) / 60000);
    let dif 	   = diffHrs + 'h ' + diffMins + 'm';
    
    this.res = dif;
  }

}

class ParamsCalcDiff {
	public dateStart: string;
  public dateStop: string;
}




