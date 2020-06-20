import { Injectable } from '@angular/core';
import { MapsAPILoader } from '@agm/core';
import { filter, catchError, tap, map, switchMap } from 'rxjs/operators';
import { Geoposition } from '@ionic-native/geolocation/ngx';
import { Observable } from 'rxjs';
import { from } from 'rxjs';
import { of } from 'rxjs';
import { AddressUser } from '../userdetails';

declare var google: any;

@Injectable({
    providedIn: 'root'
  })
export class GMapsService {
  private geocoder: any;

  constructor(private mapLoader: MapsAPILoader) { }

  private initGeocoder() {
    this.geocoder = new google.maps.Geocoder();
  }

  private waitForMapsToLoad(): Observable<boolean> {
    if (!this.geocoder) {
      return from(this.mapLoader.load())
        .pipe(tap(() => this.initGeocoder()), map(() => true));
    }
    return of(true);
  }

  geocodeAddress3(): Observable<any> {
    let location = 'Amsterdam';
    return this.waitForMapsToLoad().pipe(
      switchMap(() => {
        return new Observable(observer => {
          this.geocoder.geocode({ 'address': location }, (results, status) => {
            if (status == google.maps.GeocoderStatus.OK) {
              observer.next({
                lat: results[0].geometry.location.lat(),
                lng: results[0].geometry.location.lng()
              });
            } else {
              console.log('Error - ', results, ' & Status - ', status);
              observer.next({});
            }
            observer.complete();
          });
        })
      })
    )
  }

  private getaddress(results: any): AddressUser {
    let storableLocation: any = {};
    for (let ac = 0; ac < results[0].address_components.length; ac++) {
      let component = results[0].address_components[ac];
      storableLocation.formatted_address = results[0].formatted_address;
      switch (component.types[0]) {
        case 'locality':
          storableLocation.city = component.long_name;
          break;
        case 'administrative_area_level_1':
          storableLocation.state = component.short_name;
          break;
        case 'country':
          storableLocation.country = component.long_name;
          storableLocation.country_iso_code = component.short_name;
          break;
        case 'route':
          storableLocation.street = component.long_name;
          break;
      }
    };
    return storableLocation
  }

  geocodeAddress(position: Geoposition): Observable<any> {
    return this.waitForMapsToLoad().pipe(
      switchMap(() => {
        return new Observable(observer => {
          let latlng = { lat: position.coords.latitude, lng: position.coords.longitude };
          this.geocoder.geocode({ 'location': latlng }, (results, status) => {
            if (status == google.maps.GeocoderStatus.OK) {
              observer.next(this.getaddress(results));
            } else {
              console.log('Error - ', results, ' & Status - ', status);
              observer.next(null);
            }
            observer.complete();
          });
        })
      })
    )
  }
}