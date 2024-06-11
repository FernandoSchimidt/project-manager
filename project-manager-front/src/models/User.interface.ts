import { Project } from "./Project.interface";

export interface User {
  id: number;
  name: string;
  email: string;
  cpf: string;
  projects: Project[];
}
