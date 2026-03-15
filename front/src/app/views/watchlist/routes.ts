import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Seguimiento'
    },
    children: [
        {
            path: '',
            redirectTo: 'list',
            pathMatch: 'full'
        },
        {
            path: 'list',
            loadComponent: () => import('./watchlist-list/watchlist-list.component').then(m => m.WatchlistListComponent),
            data: {
                title: 'Lista de Seguimiento'
            }
        }
    ]
  }
];

