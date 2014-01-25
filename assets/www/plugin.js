var Wallpaper = function () {};

Wallpaper.prototype.set = function (url, successCallback, failureCallback) {
    return cordova.exec(successCallback, failureCallback, 'Wallpaper', "setWallPaper", [url]);
};
if(!window.plugins) {
    window.plugins = {};
}
if (!window.plugins.wallpaper) {
    window.plugins.wallpaper = new Wallpaper();
}