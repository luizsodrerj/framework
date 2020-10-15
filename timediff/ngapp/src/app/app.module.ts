import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { NgxMaskModule } from 'ngx-mask';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AppTdiffComponent } from './app-tdiff/app-tdiff.component';
import { CpaiComponent } from './cpai/cpai.component';
import { CfilhoComponent } from './cfilho/cfilho.component';


@NgModule({
  declarations: [
    AppComponent,
    AppTdiffComponent,
    CpaiComponent,
    CfilhoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    NgxMaskModule.forRoot(),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
