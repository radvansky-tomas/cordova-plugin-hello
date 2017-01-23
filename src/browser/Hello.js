'use strict';
/**
 * Created by tomasradvansky on 16/01/2017.
 */
var Promise = require('promise');

function greet(opts) {
    return new Promise(function (fulfill, reject) {
       reject("This plugin cannot work in browser!");
    });
}

module.exports = {
    greet: greet
};

require('cordova/exec/proxy').add('Hello', module.exports);