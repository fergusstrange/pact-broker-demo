import path from "path";
import pact from "pact";

import SessionClient from "../../../src/services/SessionClient";
import {MediaType, Method, Header} from "../../../src/services/http";

describe('ManagementPortalAPI', () => {

  jasmine.DEFAULT_TIMEOUT_INTERVAL = 15000;

  const {somethingLike: like, eachLike} = pact.Matchers;

  const port = 8890;
  const url = `http://localhost:${port}`;

  const provider = pact({
    consumer: 'managementPortalUI',
    provider: 'managementPortal',
    port: port,
    log: path.resolve(process.cwd(), 'logs',
        `mockserver-${new Date().toISOString()}.log`),
    dir: path.resolve(process.cwd(), 'pacts'),
    spec: 2
  });

  beforeAll((done) => {
    provider.setup().then(done)
    .catch(e => {
      console.log('ERROR: ', e)
    });
  });

  describe('SessionClient', () => {
    const client = new SessionClient({host: url});

    const createSessionInteraction = {
      state: 'user with an existing account',
      uponReceiving: 'a request for a new session',
      withRequest: {
        method: Method.POST,
        path: '/management-portal-api/session',
        body: {
          email: like("beresford4beer@beer.com"),
          password: like("superSecure")
        },
        headers: {
          [Header.CONTENT_TYPE]: MediaType.JSON,
          [Header.ACCEPT]: MediaType.JSON
        },
      },
      willRespondWith: {
        status: 200
      }
    };

    beforeAll((done) => {
      provider.addInteraction(createSessionInteraction)
      .then(done);
    });

    describe('user login and logout flow', () => {

      it('logs in', done => {
        const response = client.createSession("tom.beresford@lovesbeer.com",
            "really-secure");
        response.then(done)
        .catch(err => {
          done.fail(JSON.stringify(err.response.data, null, '\t'));
        });
      });

    });
  });

  afterAll(() => {
    return provider.finalize();
  });
});
