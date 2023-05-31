import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable, inject } from "@angular/core";
import { Review } from "./model";
import { Observable } from "rxjs";

const URL = 'http://localhost:8080'

@Injectable()
export class UtilityService {

    http = inject(HttpClient)

    query = ''


    searchMovie(query: string): Observable<Review[]> {
        const params = new HttpParams().set('query', query)

        return this.http.get<Review[]>(URL + '/api/search', {params})
    }

    submitComment(data: any, title: string): Observable<any> {
        const formData = new FormData
        formData.set('name', data.name)
        formData.set('rating', data.rating)
        formData.set('comment', data.comment)
        formData.set('title', title)

        return this.http.post<any>(URL + '/api/comment', formData)
    }
}