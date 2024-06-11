import { Project } from "./Project.interface";
import { User } from "./User.interface";

export interface WorkHourLog {
  id: number;
  date: string;
  hoursWorked: number;
  project:Project;
}
