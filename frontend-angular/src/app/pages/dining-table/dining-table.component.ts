import { Component, inject, OnInit, signal } from '@angular/core';
import { DiningTableService } from '../../service/dining-table.service';
import { DiningTable } from '../../model/diningtable/dining-table';

@Component({
  selector: 'app-dining-table',
  standalone: true,
  imports:[],
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



  getAll(){
    this.tableService.listAll().subscribe({
      next: lista =>{
        this.tables.set(lista);
      }
    });
  }

  

}
