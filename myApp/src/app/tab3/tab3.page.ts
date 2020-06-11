import { Component } from '@angular/core';
import { ConfigService, Employee } from '../services/config.service';

@Component({
  selector: 'app-tab3',
  templateUrl: 'tab3.page.html',
  styleUrls: ['tab3.page.scss'],
  providers: [ ConfigService ],
  styles: ['.error {color: red;}']
})
export class Tab3Page {

  error: any;
  headers: string[];
  config: Employee[];

  constructor(private configService: ConfigService) {}

  clear() {
    this.config = undefined;
    this.error = undefined;
    this.headers = undefined;
  }

  showConfig() {
    this.configService.getConfig()
      .subscribe(
        (data: Employee[]) => this.config = { ...data }, // success path
        error => this.error = error// error path
        
      );
      
  }
/*
  showConfig_v1() {
    this.configService.getConfig_1()
      .subscribe((data: Employee) => this.config = {
          id: data['id'],
          type:  data['type'],
          punchline:  data['punchline'],
          setup:  data['setup']
      });
  }
  */

  showConfig_v2() {
    this.configService.getConfig()
      // clone the data object, using its known Config shape
      .subscribe((data: Employee[]) => this.config = { ...data });
  }

  showConfigResponse() {
    this.configService.getConfigResponse()
      // resp is of type `HttpResponse<Config>`
      .subscribe(resp => {
        // display its headers
        const keys = resp.headers.keys();
        this.headers = keys.map(key =>
          `${key}: ${resp.headers.get(key)}`);

        // access the body directly, which is typed as `Config`.
        this.config = { ... resp.body };
      });
  }
  makeError() {
    this.configService.makeIntentionalError().subscribe(null, error => this.error = error );
  }

}
