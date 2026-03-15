import { Component, effect, inject, input, output } from '@angular/core';
import { DiningTable } from '../../model/diningtable/dining-table';
import { FormsModule } from '@angular/forms';
import { DiningTableStatus } from '../../model/diningtable/dining-table-status';
import { DiningTableService } from '../../service/dining-table.service';

@Component({
  selector: 'app-table-modal',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './table-modal.component.html',
  styleUrl: './table-modal.component.css'
})
export class TableModalComponent {

  diningTableStatus = DiningTableStatus;
  private tableService = inject(DiningTableService);

  saved = output<void>();
  close = output<void>();

  table = input<DiningTable | undefined>(undefined);

  tableData = {
    id: 0,
    name: '',
    capacity: 0,
    status: 'AVAILABLE'
  } as DiningTable;

  constructor() {
    effect(() => {
      const table = this.table();
      if (table) {
        this.tableData = { ...table };
      } else {
        this.tableData = {
          id: 0,
          name: '',
          capacity: 0,
          status: 'AVAILABLE'
        } as DiningTable;
      }
    })
  }
  getKeys(obj: any) { return Object.keys(obj); }

  save() {
    const request = this.tableData.id > 0
      ? this.tableService.update(this.tableData.id, this.tableData)
      : this.tableService.save(this.tableData);

    request.subscribe({
      next: () => {
        this.saved.emit();
        this.close.emit();
      }, error: err => {
        alert(err.error.message);
      }
    })
  }

  delete() {
    this.tableService.delete(this.tableData.id).subscribe({
      next: () => {
        this.saved.emit();
        this.close.emit();
      },
      error: err => {
        alert(err.error.message);
      }
    })
  }
}


