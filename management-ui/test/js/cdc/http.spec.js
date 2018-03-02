import {Http} from "../../../src/services/http"
import nock from 'nock';

describe('Testing Our Http Client', () => {

  describe("A client", () => {
    test('should return successful promise when 200', (done) => {
      nock('http://localhost:5555')
      .get('/200')
      .reply(200, 'OK');

      Http.get('http://localhost:5555/200')
      .then(done)
      .catch((error) => console.log(error));
    });

    test('should return unsuccessful promise when 400', (done) => {
      nock('http://localhost:5555')
      .get('/400')
      .reply(400);

      Http.get('http://localhost:5555/400')
      .catch(done);
    });

    test('should return unsuccessful promise when 401', (done) => {
      nock('http://localhost:5555')
      .get('/401')
      .reply(401);

      Http.get('http://localhost:5555/401')
      .catch(done);
    });

    test('should return unsuccessful promise when 403', (done) => {
      nock('http://localhost:5555')
      .get('/403')
      .reply(403);

      Http.get('http://localhost:5555/403')
      .catch(done);
    });

    test('should return unsuccessful promise when 500', (done) => {
      nock('http://localhost:5555')
      .get('/500')
      .reply(500);

      Http.get('http://localhost:5555/500')
      .catch(done);
    });

    test('should return unsuccessful promise when 503', (done) => {
      nock('http://localhost:5555')
      .get('/503')
      .reply(503);

      Http.get('http://localhost:5555/503')
      .catch(done);
    });

    test('should return unsuccessful promise when 504', (done) => {
      nock('http://localhost:5555')
      .get('/504')
      .reply(504);

      Http.get('http://localhost:5555/504')
      .catch(done);
    });
  });
});
