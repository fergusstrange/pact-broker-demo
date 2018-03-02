import {Method, managementApi, HttpClient} from "./http";

class SessionClient extends HttpClient {

  /**
   * Gets a session based on login credentials
   *
   * @param {string} email
   * @param {string} password
   */
  createSession(email, password) {
    return this.request(Method.POST, managementApi('/session'), {email, password});
  }

  deleteSession() {
    return this.request(Method.DELETE, managementApi('/session'));
  }
}

export default SessionClient;
