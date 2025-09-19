import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [
    RouterLink
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  name: string = '';

  ngOnInit(): void {
    let fullname = localStorage.getItem('fullname');
    if (fullname) {
      this.name = fullname;
    }
  }


}
