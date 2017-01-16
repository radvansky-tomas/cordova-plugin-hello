/**
 * Created by tomasradvansky on 16/01/2017.
 */

function greet(success, error, opts) {
    success('dummy');
}

module.exports = {
    greet: greet
};

require('cordova/exec/proxy').add('Hello', module.exports);