import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UtilityService } from '../utils.service';
import { Review } from '../model';
import { Observable } from 'rxjs';
import { Params, Router } from '@angular/router';

@Component({
  selector: 'app-search-review',
  templateUrl: './search-review.component.html',
  styleUrls: ['./search-review.component.css']
})
export class SearchReviewComponent implements OnInit {

    fb = inject(FormBuilder)
    utility = inject(UtilityService)
    router = inject(Router)

    form!: FormGroup
    reviews$!: Observable<Review>


    ngOnInit(): void {
        this.form = this.fb.group({
            movie: this.fb.control<string>('', [ Validators.required, Validators.minLength(2)]),
        })
    }

    searchMovie(query: any) {
        console.info(query)
        const queryParams: Params = { query : query}
        this.utility.searchMovie(query).subscribe({
            next: () => {this.router.navigate(['/view1'], {queryParams})}
        })
    }
    
    invalid(): boolean {
        return this.form.invalid || this.form.value['movie'].trim().length < 2
    }
}
