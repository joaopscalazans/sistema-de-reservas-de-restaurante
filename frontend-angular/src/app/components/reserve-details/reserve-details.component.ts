import { Component, input, signal } from '@angular/core';
import { DiningTable } from '../../model/diningtable/dining-table';

@Component({
  selector: 'app-reserve-details',
  standalone: true,
  imports: [],
  templateUrl: './reserve-details.component.html',
  styleUrl: './reserve-details.component.css'
})
export class ReserveDetailsComponent {

  table = input<DiningTable | null>(null);

}
