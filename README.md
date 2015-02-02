Cordova Evan Plugin PhoneTrap
=============================

Phone calls event handling for Cordova apps running in Android devices.

Based on [Cordova PhoneCall Trap](https://github.com/renanoliveira/cordova-phone-call-trap)

Install
-------

```sh
cordova plugin add https://github.com/tassoevan/cordova-plugin-evan-phonetrap.git
```

Usage
-----

```js
cordova.Evan.onCall(function (state) {
    switch (state) {
        case cordova.Evan.PHONE_STATE_IDLE:
            receivedElement.textContent = "idle";
            break;

        case cordova.Evan.PHONE_STATE_OFFHOOK:
            receivedElement.textContent = "offhook";
            break;

        case cordova.Evan.PHONE_STATE_RINGING:
            receivedElement.textContent = "ringing";
            break;

        case cordova.Evan.PHONE_STATE_UNKNOWN:
            receivedElement.textContent = "unknown";
            break;
    }
});
```