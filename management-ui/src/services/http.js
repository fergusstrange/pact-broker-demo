import axios from "axios";
import {defaults} from 'lodash';

/**
 * Upstream customer sign-up api context path configuration
 * @param {string} path - the path to append to the context
 * @returns {string} the path with context prefixed
 */
export const managementApi = path => `/management-portal-api${path}`;

/**
 * Enumeration to track ajax request states
 */
export const LoadingState = {
  SUCCESS: 'SUCCESS',
  FAILED: 'FAILED',
  PENDING: 'PENDING'
};

/**
 * Http verbs
 */
export const Method = {
  GET: 'GET',
  POST: 'POST',
  DELETE: 'DELETE',
  PUT: 'PUT',
  PATCH: 'PATCH'
};

/**
 * Http headers
 */
export const Header = {
  CONTENT_TYPE: 'Content-Type',
  ACCEPT: 'Accept'
};

/**
 * Http method types
 */
export const MediaType = {
  JSON: 'application/json'
};

/**
 * Instantiate a http client so we can register interceptors
 */
export const Http = axios.create({});

Http.interceptors.response.use(
    (response) => response,
    (error) => {
      if (error.response && error.response.status === 401) {
        const currentLocation = encodeURIComponent(window.location.pathname);
        window.location = `/signin?next=${currentLocation}`;
      }
      return Promise.reject(error);
    });

/**
 * Http client
 */
export class HttpClient {
  constructor(options) {
    this.options = defaults(options, {host: '', headers: {}});
  }

  /**
   * Makes a http request using axios http client
   * @param {string} method - the http verb
   * @param {string} path - the path
   * @param {object?} data - the request body
   * @returns {*}
   */
  request = (method, path, data) => {
    const defaultHeaders = {
      [Header.ACCEPT]: MediaType.JSON,
      [Header.CONTENT_TYPE]: MediaType.JSON
    };

    return Http({
      url: path,
      baseURL: this.options.host,
      method: method,
      headers: Object.assign({}, defaultHeaders, this.options.headers),
      data: data,
      credentials: 'same-origin'
    });
  }
}
