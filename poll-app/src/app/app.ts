import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { pollc } from './poll/pollc';

@Component({
  selector: 'app-root',
  imports: [pollc],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('poll-app');
}
