import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JsonResponse } from '@model/json-response.class';
import { Observable } from 'rxjs';
import { PurchaseRequestLineItem } from '@model/purchase-request-line-item.class';


@Injectable({
  providedIn: 'root'
})
export class PurchaseRequestLineItemService {

  url: string = "http://localhost:8080/purchaserequestlineitem/";

  constructor(private http: HttpClient) { }

  list(): Observable<JsonResponse> {
    return this.http.get(this.url) as Observable<JsonResponse>;
  }

  create(prli: PurchaseRequestLineItem): Observable<JsonResponse> {
    return this.http.post(this.url, prli) as Observable<JsonResponse>;
  }

  linesForPr(prid: string): Observable<JsonResponse> {
  return this.http.get(this.url+"lines-for-pr/"+prid) as Observable<JsonResponse>
  }
  
  edit(prli: PurchaseRequestLineItem): Observable<JsonResponse> {
    return this.http.put(this.url, prli) as Observable<JsonResponse>;
  }

  get(prliId: string): Observable<JsonResponse> {
    return this.http.get(this.url + prliId) as Observable<JsonResponse>;
    
  }
  remove(prliId: PurchaseRequestLineItem): Observable<JsonResponse>{
    return this.http.delete(this.url + prliId.id) as Observable<JsonResponse>;

  }

  }

