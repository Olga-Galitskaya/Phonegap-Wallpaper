var TestPlugin = function () {};

TestPlugin.prototype.set = function (ms, successCallback, failureCallback) {
//  navigator.notification.alert("OMG");
    return cordova.exec(successCallback, failureCallback, 'testPlugin', "setWallPaper", [ms]);
};

PhoneGap.addConstructor(function() {
    PhoneGap.addPlugin("test", new TestPlugin());
})