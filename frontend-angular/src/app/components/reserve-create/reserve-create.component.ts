import { Component, inject, input, output } from '@angular/core';
import { DiningTable } from '../../model/diningtable/dining-table';
import { ReserveService } from '../../service/reserve.service';
import { ReserveRequest } from '../../model/reserve/reserveRequest';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../service/auth.service';

@Component({
  selector: 'app-reserve-create',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './reserve-create.component.html',
  styleUrl: './reserve-create.component.css'
})
export class ReserveCreateComponent {

  logo = "assets/mesa-de-jantar.png"
  table = input.required<DiningTable>();
  close = output<void>();
  private reserveService = inject(ReserveService);
  private authService = inject(AuthService)

  date: String | null = null;
  time: String | null = null;

  getRole(){
    return this.authService.getRole();
  }

  create() {
    if (!this.date || !this.time) {
      alert("Escolha data e hora para continuar");
      return;
    }
    const dateTime = `${this.date}T${this.time}`;
    const id = this.table().id

    const reserve = {
      date: dateTime,
      table: id
    } as ReserveRequest

    this.reserveService.save(reserve).subscribe({
      next: () => {
        alert("Sua reserva foi feita com sucesso")
        this.close.emit()
      },
      error: err => {
        alert(err.error.message) 
      }
    })
  }
}
