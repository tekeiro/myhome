import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '',
        data: {
            title: 'Busqueda'
        },
        loadComponent: () => import('./search.component').then(m => m.SearchComponent)
    }
];