import { Component, inject, signal } from '@angular/core';
import { DiningTableService } from '../../service/dining-table.service';
import { DiningTable } from '../../model/diningtable/dining-table';

@Component({
  selector: 'app-dining-table',
  standalone: true,
  imports: [],
  templateUrl: './dining-table.component.html',
  styleUrl: './dining-table.component.css'
})
export class DiningTableComponent {

  private tableService = inject(DiningTableService)
  tables = signal<DiningTable[]>([]);
  userRole: String;

  constructor(){
    this.userRole = ""
    this.getAll();
  }

  getAll(){
    this.tableService.listAll().subscribe({
      next: lista =>{
        this.tables.set(lista);
      }
    });
  }
  

}
