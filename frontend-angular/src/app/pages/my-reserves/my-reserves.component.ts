import { Component, effect, inject, OnInit, signal } from '@angular/core';
import { ReserveService } from '../../service/reserve.service';
import { Reserve } from '../../model/reserve/reserve';
import { ReserveDetailsComponent } from "../../components/reserve-details/reserve-details.component";
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-my-reserves',
  standalone: true,
  imports: [ReserveDetailsComponent, DatePipe],
  templateUrl: './my-reserves.component.html',
  styleUrl: './my-reserves.component.css'
})
export class MyReservesComponent implements OnInit {
  ngOnInit(): void {
    this.loadReserves();
  }

  private reserveService = inject(ReserveService);
  lista = signal<Reserve[]>([]);
  showModal = signal(false);
  selectReserve = signal<Reserve | undefined>(undefined);

 openModal(reserve:Reserve){
    this.selectReserve.set(reserve);
    this.showModal.set(true);
  }
  closeModal(){
    this.selectReserve.set(undefined)
    this.showModal.set(false)
  }

  loadReserves(){ 
    this.reserveService.getAll().subscribe({
      next: res => {
        this.lista.set(res);
      }
    })
  }

  cancel(id: number){
    this.reserveService.cancel(id).subscribe({
      next: () =>{
        alert("Reserva cancelada com sucesso")
        this.loadReserves();
      },
      error: err => {
        alert(err.error.message);
      }
    })
  }

}
