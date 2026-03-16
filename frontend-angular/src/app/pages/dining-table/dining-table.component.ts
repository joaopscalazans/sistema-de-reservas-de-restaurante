import { Component, inject, OnInit, signal } from '@angular/core';
import { DiningTableService } from '../../service/dining-table.service';
import { DiningTable } from '../../model/diningtable/dining-table';
import { ReserveCreateComponent } from "../../components/reserve-create/reserve-create.component";
import { TableModalComponent } from "../../components/table-modal/table-modal.component";

@Component({
  selector: 'app-dining-table',
  standalone: true,
  imports: [TableModalComponent, ReserveCreateComponent],
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
  showModal = signal(false);

  openDetails(table:DiningTable){
    this.selectTable.set(table);
  }
  closeDetails(){
    this.selectTable.set(undefined)
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
