import { Component, inject, OnInit, signal } from '@angular/core';
import { DiningTableService } from '../../service/dining-table.service';
import { DiningTable } from '../../model/diningtable/dining-table';
import { ReserveDetailsComponent } from "../../components/reserve-details/reserve-details.component";
import { TableModalComponent } from "../../components/table-modal/table-modal.component";

@Component({
  selector: 'app-dining-table',
  standalone: true,
  imports: [ReserveDetailsComponent, TableModalComponent],
  templateUrl: './dining-table.component.html',
  styleUrl: './dining-table.component.css'
})
export class DiningTableComponent implements OnInit {
  
  ngOnInit(): void {
   this.getAll();
  }

  private tableService = inject(DiningTableService)
  tables = signal<DiningTable[]>([]);
  authorized = signal(localStorage.getItem("role") === "ADMIN")
  selectTable = signal<DiningTable | undefined>(undefined)
  editingTable = signal<DiningTable | undefined>(undefined);
  showModal = signal<boolean>(false);

  openDetails(table:DiningTable){
    this.selectTable.set(table);
  }

  openModal(table?:DiningTable){
    if(table) this.editingTable.set(table);
    else this.editingTable.set(undefined);

    this.showModal.set(true);
  }
  closeModal(){
    this.showModal.set(false)
  }

  getAll(){
    this.tableService.listAll().subscribe({
      next: lista =>{
        this.tables.set(lista);
      }
    });
  }



  

}
