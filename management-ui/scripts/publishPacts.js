/**
 * Publishes pacts to the pact broker
 */

"use strict";

let path = require('path');
let pact = require('@pact-foundation/pact-node');

let opts = {
  pactUrls: [path.resolve(process.cwd(), 'pacts')],
  pactBroker: process.env.PACT_BROKER_URL || 'http://localhost:8000',
  pactBrokerUsername: process.env.PACT_BROKER_USERNAME || 'foo',
  pactBrokerPassword: process.env.PACT_BROKER_PASSWORD || 'bar',
  consumerVersion: '0'
};

pact.publishPacts(opts)
.then(() => {
  console.log('Published Pacts');
});