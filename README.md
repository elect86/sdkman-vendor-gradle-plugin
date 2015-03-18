#SDK Vendor Plugin

This Gradle plugin is used by SDK Vendors to publish their own releases on GVM/SDKenv. Even more, they are able to announce their releases through the Broadcast API.

##Vendors

A Vendor is an organisation/individual that owns a technology, and wishes to publish it's Versions on GVM.

###Setup
It requires some configuration in the `build.gradle` file of the project.

        buildscript {
                repositories {
                        ...
                        maven { url 'http://dl.bintray.com/vermeulen-mp/gradle-plugins' }
                }
                dependencies {
                        classpath 'net.gvmtool:gradle-sdkvendor-plugin:0.1.0'
                }
        }

        apply plugin: 'net.gvmtool.sdkvendor'

You will also need some configuration to interact with the remote API in order to publish and broadcast:

	gvm {
		api = "https://gvm-vendor.herokuapp.com/release"
		consumerKey = "SOME_KEY"
		consumerToken = "SOME_TOKEN"
		candidate = "grails"
		version = "3.0.0"
		url = "http://dist.springframework.org.s3.amazonaws.com/release/GRAILS/grails-3.0.0.zip"
		hashtag = "#grailsfw"
	}

####Credentials

The credentials for Vendors consisting of a security key and token can be obtained by raising an [Issue on Github](https://github.com/gvmtool/gradle-sdkvendor-plugin/issues).

####Other Config

Other configuration such as `candidate`, `version`, `url` and `hashtag` may be populated dynamically in the build.