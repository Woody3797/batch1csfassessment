import { Component, OnInit, inject } from '@angular/core';
import { UtilityService } from '../utils.service';
import { Observable, Subscription } from 'rxjs';
import { Review } from '../model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-movie-reviews-list',
  templateUrl: './movie-reviews-list.component.html',
  styleUrls: ['./movie-reviews-list.component.css']
})
export class MovieReviewsListComponent implements OnInit {

    utility = inject(UtilityService)
    activatedRoute = inject(ActivatedRoute)

    reviewsObs$!: Observable<Review[]>
    reviews$!: Subscription
    query = ''
    link!: string

    ngOnInit() {
        this.query = this.activatedRoute.snapshot.queryParams['query']
        this.utility.query = this.query
        this.reviewsObs$ = this.utility.searchMovie(this.query)
    }



}
