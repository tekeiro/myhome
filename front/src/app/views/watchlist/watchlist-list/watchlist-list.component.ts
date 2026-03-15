import { Component, inject } from '@angular/core';
import {
  AlignDirective,
  BorderDirective,
  CardBodyComponent,
  CardComponent,
  CardHeaderComponent,
  ColComponent,
  RowComponent,
  TableActiveDirective,
  TableColorDirective,
  TableDirective
} from '@coreui/angular';
import {WatchlistItemControllerService} from "../../../../client/services/watchlistItemController.service";
import {PageRequest, PageResultWatchlistItem, WatchlistItem, WatchlistItemNode} from "../../../../client/models/index";
import { toSignal } from '@angular/core/rxjs-interop';


@Component({
  selector: 'app-watchlist-list',
  imports: [RowComponent, ColComponent, CardComponent, CardHeaderComponent, CardBodyComponent, TableDirective, TableColorDirective, TableActiveDirective, BorderDirective, AlignDirective],
  templateUrl: './watchlist-list.component.html',
  styleUrl: './watchlist-list.component.scss',
})
export class WatchlistListComponent {
  private watchListService = inject(WatchlistItemControllerService);

  
}
