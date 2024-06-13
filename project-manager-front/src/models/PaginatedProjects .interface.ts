import { Project } from "./Project.interface";

export interface PaginatedProjects {
  content: Project[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
  first: boolean;
  last: boolean;
  numberOfElements: number;
}
