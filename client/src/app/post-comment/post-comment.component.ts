import { Component, OnInit, inject } from '@angular/core';
import { UtilityService } from '../utils.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';

@Component({
  selector: 'app-post-comment',
  templateUrl: './post-comment.component.html',
  styleUrls: ['./post-comment.component.css']
})
export class PostCommentComponent implements OnInit {

    utility = inject(UtilityService)
    fb = inject(FormBuilder)
    activatedRoute = inject(ActivatedRoute)
    router = inject(Router)

    form!: FormGroup
    pathV = ''
    query = ''

    ngOnInit(): void {
        this.form = this.fb.group({
            name: this.fb.control('', [Validators.required, Validators.minLength(3)]),
            rating: this.fb.control(3, [Validators.required, Validators.min(1), Validators.max(5)]),
            comment: this.fb.control('', [Validators.required])
        })

        this.pathV = this.activatedRoute.snapshot.params['display_title']
        this.query = this.utility.query
    }

    submit() {
        console.info(this.pathV)
        this.utility.submitComment(this.form.value, this.pathV).subscribe()
        const queryParams: Params = { query : this.query}
        this.router.navigate(['/view1'], {queryParams})
    }

    invalid() {
        return this.form.invalid || this.form.value['comment'].trim().length < 1 || this.form.value['name'].trim().length < 2
    }
}
