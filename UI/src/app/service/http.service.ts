import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";

@Injectable()
export class HttpService {


  private coreServerUrl = "http://localhost:8080/core/"
  private httpOptions: any;
  constructor(
    private http: HttpClient
  ) {

    console.log('Init. Form-Api-Service...');


    this.httpOptions = { headers: new HttpHeaders().set('Content-Type', 'application/json') };

  }

  getDataFromCoreServer(url) {
    let finalUrl = this.coreServerUrl + url;
    console.log("url " + finalUrl)
    return this.http.get<any>(finalUrl);//.map((response: Response) => <FormModel>response.json());
  }

  getData(ipAddress, port, url) {
    let finalUrl = `http://$${ipAddress}:${port}/client/${url}`;
    console.log("url " + finalUrl)
    return this.http.get<any>(finalUrl);//.map((response: Response) => <FormModel>response.json());
  }
  postDataAbsoluteURL(url: string, data: any) {

    return this.http.post<any>(url, data, this.httpOptions)

  }




}
