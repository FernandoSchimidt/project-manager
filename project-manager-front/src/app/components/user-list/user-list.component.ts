import { Component } from '@angular/core';
import { UserService } from '../../services/user.service';
import { User } from '../../../models/User.interface';
import { MatToolbarModule } from '@angular/material/toolbar';
import { UpperCasePipe } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-user-list',
  standalone: true,
  imports: [MatToolbarModule, UpperCasePipe, RouterLink],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.scss',
})
export class UserListComponent {
  usersList: User[] = [];
  constructor(private userService: UserService) {
    this.findAllUsers();
  }

  findAllUsers() {
    this.userService.getUsers().subscribe((res) => {
      this.usersList = res;
    });
  }
}
