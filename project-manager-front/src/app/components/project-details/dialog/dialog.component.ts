import { Component, Inject, inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { Project } from '../../../../models/Project.interface';
import { MatCardModule } from '@angular/material/card';
import { DatePipe } from '@angular/common';

import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatFormFieldModule } from '@angular/material/form-field';
import { provideNativeDateAdapter } from '@angular/material/core';
import { WorkHourRequest } from '../../../../models/WorkHourRequest';
import { FormsModule } from '@angular/forms';
import { WorkHourLogService } from '../../../services/work-hour-log.service';

@Component({
  selector: 'app-dialog',
  standalone: true,
  imports: [
    MatCardModule,
    MatInputModule,
    MatDatepickerModule,
    MatButtonModule,
    DatePipe,
    MatFormFieldModule,
    FormsModule,
  ],
  templateUrl: './dialog.component.html',
  styleUrl: './dialog.component.scss',
  providers: [provideNativeDateAdapter()],
})
export class DialogComponent {
  service = inject(WorkHourLogService);
  selected?: Date | null;
  workHour: WorkHourRequest = {
    date: '',
    hoursWorked: 0,
  };
  constructor(
    public dialogRef: MatDialogRef<DialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Project
  ) {}

  onNoClick(): void {
    this.dialogRef.close();
  }

  myFilter = (d: Date | null): boolean => {
    const day = (d || new Date()).getDay();
    if (d) {
      const startDate = new Date(this.data.startDate);
      const endDate = new Date(this.data.endDate);
      if (d < startDate || d > endDate) {
        return false;
      }
    }
    return day !== 0 && day !== 6;
  };

  workHourLog() {
    if (this.workHour.date) {
      const date = new Date(this.workHour.date);
      const year = date.getFullYear();
      const month = ('0' + (date.getMonth() + 1)).slice(-2);
      const day = ('0' + date.getDate()).slice(-2);
      this.workHour.date = `${year}-${month}-${day}`;
    }
    this.service.workHourLog(this.workHour, this.data.id).subscribe((res) => {
      this.onNoClick();
      window.location.reload();
    });
  }
}
