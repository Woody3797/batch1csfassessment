import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { SearchReviewComponent } from './search-review/search-review.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { UtilityService } from './utils.service';
import { MovieReviewsListComponent } from './movie-reviews-list/movie-reviews-list.component';
import { PostCommentComponent } from './post-comment/post-comment.component';

const routes: Routes = [
    {path: '', component: SearchReviewComponent },
    {path: 'view1', component: MovieReviewsListComponent },
    {path: 'view2/:display_title', component: PostCommentComponent },
    {path: '**', redirectTo: '/', pathMatch: 'full' }
]

@NgModule({
  declarations: [
    AppComponent,
    SearchReviewComponent,
    MovieReviewsListComponent,
    PostCommentComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(routes)
  ],
  providers: [ UtilityService ],
  bootstrap: [AppComponent]
})
export class AppModule { }
