import { Component, input, output } from '@angular/core';
import { Reserve } from '../../model/reserve/reserve';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-reserve-details',
  standalone: true,
  imports: [DatePipe],
  templateUrl: './reserve-details.component.html',
  styleUrl: './reserve-details.component.css'
})
export class ReserveDetailsComponent {

  reserve = input.required<Reserve>();
  cancel = output<number>();
  close = output<void>();

  alertCancel(){
    const result = confirm("Você tem certeza que quer cancelar??")

    if(result){
      this.cancel.emit(this.reserve().id)
    }
  }
}
