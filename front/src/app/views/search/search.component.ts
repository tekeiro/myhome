import { Component, inject } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {
  ButtonDirective,
  CardBodyComponent,
  CardComponent,
  CardHeaderComponent,
  ColComponent,
  ColDirective,
  FormControlDirective,
  FormDirective,
  FormLabelDirective,
  GutterDirective,
  RowComponent,
  RowDirective,
  InputGroupComponent,
  InputGroupTextDirective,
  TableDirective
} from '@coreui/angular';
import { RequestOptions, SearchResult } from 'src/client/models';
import { SearchControllerService } from "src/client/services/searchController.service";

@Component({
  selector: 'app-search',
  imports: [RowComponent, InputGroupComponent, TableDirective, InputGroupTextDirective, ColComponent, CardComponent, CardHeaderComponent, CardBodyComponent, ReactiveFormsModule, FormsModule, FormDirective, FormLabelDirective, FormControlDirective, ButtonDirective, RowDirective, GutterDirective, ColDirective],
  templateUrl: './search.component.html',
  styleUrl: './search.component.scss',
})
export class SearchComponent {
  searchText: string = '';
  searchService = inject(SearchControllerService);
  searchResults: SearchResult[] = [];

  doSearch() {
    this.searchService.search(this.searchText).subscribe(searchResults => {
      this.searchResults = searchResults;
    });
  }
}
