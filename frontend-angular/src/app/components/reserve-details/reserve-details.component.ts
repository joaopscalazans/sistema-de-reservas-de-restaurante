import { Component, inject, input, output} from '@angular/core';
import { DiningTable } from '../../model/diningtable/dining-table';
import { ReserveService } from '../../service/reserve.service';
import { ReserveRequest } from '../../model/reserve/reserveRequest';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-reserve-details',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './reserve-details.component.html',
  styleUrl: './reserve-details.component.css'
})
export class ReserveDetailsComponent {

  table = input.required<DiningTable>();
  close = output<void>();
  private reserveService = inject(ReserveService);

  date: String = "";
  time: String = ""

  create(){
    const dateTime = this.date + "T" +this.time;
    const id = this.table().id

    const reserve = {
        date: dateTime,
        table: id
    } as ReserveRequest

    this.reserveService.save(reserve).subscribe({
      next: ()=>{
        alert("Sua reserva foi feita com sucesso")
        this.close.emit()
      },
      error: err => {
        alert(err.error.message)
      }
    })
  }
}
