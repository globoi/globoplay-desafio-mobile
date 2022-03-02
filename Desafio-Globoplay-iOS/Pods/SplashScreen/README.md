# SplashScreen

[![Swift 5](https://img.shields.io/badge/Swift-5.0-orange.svg)](https://swift.org)  
[![Version](https://img.shields.io/cocoapods/v/SplashScreen.svg?style=flat)](https://cocoapods.org/pods/SplashScreen)
[![License](https://img.shields.io/cocoapods/l/SplashScreen.svg?style=flat)](https://cocoapods.org/pods/SplashScreen)
[![Platform](https://img.shields.io/cocoapods/p/SplashScreen.svg?style=flat)](https://cocoapods.org/pods/SplashScreen)

Your app's great new features were buried deep inside on the new AppStore. SplashScreen makes those features visible to your clients.

# Features

SplashScreen supports 1 header, up to 4 featured highlights (icon + text) and 1 footnote (icon + text, under development).
You can configure it to muliple languages (localization key), multiple apps (app key), multiple versions (version key) and multiple splash screens (key key :-)) ). 
Icons are cached on local device for greater performance.

## Example

To run the example project, clone the repo, and run `pod install` from the Example directory first.

```swift
LaunchSplash.showSplashScreen(caller: self,
                              app: "marcelmendes.splashscreen",
                              version: "1.0",
                              key: "splash",
                              localization: "EN",
                              hostName: "www.shopcolecao.com/",
                              splashEndpoint: "retrieveSplash.php",
                              httpHeader: "https://",
                              imageHostName: "www.shopcolecao.com/",
                              imagePath: "Images/")

```
Explaining some parameters:
hostName:  the URL of the host where your retrieveSplash.php is deployed.
splashEndpoint: if you change retrieveSplash.php name to other name, update it here too.
httpHeader: no need to explain this.
imageHostName: the URL of the host where your icons are available for retrieval.
imagePath: the path inside the host where your icons are available for retrieval.

## Screen Shot

![splashscreen screenshot](screenshot-1.png)


## Server side requirements

PHP 6 or greater and MySQL.

## Installation

Server side:
- Run MySQL_Table scripts on your MySQL installation
- Edit MySQL user account information on retrieveSplash.php
- Put retrieveSplash.php on your webserver directory

Recommendation: test your server side installation with Postman before integrating SplashScreen into your app

PHP and database files are available inside SplashScreen/Example/Server side folder.

iOS side:
SplashScreen is available through [CocoaPods](https://cocoapods.org). To install
it, simply add the following line to your Podfile:

```ruby
pod 'SplashScreen'
```

## Author

Marcel Mendes Filho, marcel.mendes@gmail.com

## License

SplashScreen is available under the MIT license. See the LICENSE file for more info.
