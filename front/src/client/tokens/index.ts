import { InjectionToken } from "@angular/core";
import { HttpInterceptor, HttpContextToken } from "@angular/common/http";

/**
 * Injection token for the MyHomeApiClient client base API path
 */
export const BASE_PATH_MYHOMEAPICLIENT = new InjectionToken<string>('BASE_PATH_MYHOMEAPICLIENT', {
    providedIn: 'root',
    factory: () => '/api', // Default fallback
});
/**
 * Injection token for the MyHomeApiClient client HTTP interceptor instances
 */
export const HTTP_INTERCEPTORS_MYHOMEAPICLIENT = new InjectionToken<HttpInterceptor[]>('HTTP_INTERCEPTORS_MYHOMEAPICLIENT', {
    providedIn: 'root',
    factory: () => [], // Default empty array
});
/**
 * HttpContext token to identify requests belonging to the MyHomeApiClient client
 */
export const CLIENT_CONTEXT_TOKEN_MYHOMEAPICLIENT = new HttpContextToken<string>(() => 'MyHomeApiClient');
