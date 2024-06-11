import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProjectService } from '../../services/project.service';
import { Project } from '../../../models/Project.interface';
import { DatePipe } from '@angular/common';
import { WorkHourLogService } from '../../services/work-hour-log.service';
import { WorkHourLog } from '../../../models/WorkHourLog .interface';
import { MatIconModule } from '@angular/material/icon';
import {
  MatDialog,
  MatDialogModule,
} from '@angular/material/dialog';
import { DialogComponent } from './dialog/dialog.component';
@Component({
  selector: 'app-project-details',
  standalone: true,
  imports: [DatePipe, MatIconModule, MatDialogModule],
  templateUrl: './project-details.component.html',
  styleUrl: './project-details.component.scss',
})
export class ProjectDetailsComponent implements OnInit {
  project: Project | undefined;
  workHours: WorkHourLog[] = [];
  constructor(
    private route: ActivatedRoute,
    private serveice: ProjectService,
    private workService: WorkHourLogService,
    public dialog: MatDialog,
    private workHourLogService: WorkHourLogService
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.serveice.findyById(id).subscribe((project) => {
      this.project = project;
    });
    this.getAllWorkedHours(id);
  }

  getAllWorkedHours(idProject: number) {
    this.workService.getWorkHourLogs(idProject).subscribe((workHoursRes) => {
      this.workHours = workHoursRes;
    });
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(DialogComponent, {
      data: this.project,
    });
    dialogRef.afterClosed().subscribe((result) => {
      console.log('The dialog was closed');
    });
  }

  deleteWorkHourLog(id: number) {
    this.workHourLogService.deleteWorkHourLog(id).subscribe(() => {
      const id = Number(this.route.snapshot.paramMap.get('id'));
      this.getAllWorkedHours(id);
    });
  }
}
