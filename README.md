mudroid-workshop-1
==================
Created by Matthew Browne for Mudroid, the Monash University Android Community
Last Edited 05/05/2012 


PROJECT: Hello Mudroid								
AIM: To create an Android app that shows a map with the function of finding the users location and that uses the actionbar design Pattern.
COMPATIBLE Android versions: 2.1 -> 4.0.3 (which is sdk version 7 -> 15)

Preparation
-----------
* Setup Eclipse IDE and ADT plugin http://developer.android.com/sdk/eclipse-adt.html
* Through the Android SDK Manager in the menu bar in Eclipse install under:
	- Tools:
		- Android SDK Tools
		- Android SDK Platform-tools
	- API 7 and 15:
	 	- SDK Platform
		- Google APIs
	- Extras:
		- Android Support
		
- Set adb in your computers PATH variable see under section "OS Specific Instructions": http://wiki.cyanogenmod.com/wiki/Howto:_Install_the_Android_SDK
- Create sdk target 7 (android 2.1 eclair) and 15 (android 4.0.3 ICS) virtual devices through ADT (if you don't have those on any physical device)

Now we are ready to start making our app

Checkpoint 0 - Setting up the project
-------------------------------------
1. Create Android Project Named : 	Hello Mudroid
2. Build Target SDK: 				Google APIs 4.0.3
3. Package Name:					com.mudroid.hellomudroid

This will only run on an ICS - 4.0.X device.
Edit AndroidManifest.xml min,max and target sdk versions to get it to run 2.1 - 4.0.3

with:

```xml
<uses-sdk
    android:maxSdkVersion="15"
    android:minSdkVersion="7"
    android:targetSdkVersion="15" />
```

Run... on both 2.1 and 4.0.3

Checkpoint 1 - adding the actionbar design pattern (using ActionBarSherlock - ABS)
----------------------------------------------------------------------------------
ABS allows for a consistent UI from sdk 7 -> 15: 
Download the ABS 4.0 zip from the home page http://actionbarsherlock.com/index.html
Click More... to get the maps jar plugin, we'll need this for our map.
Unzip the 4.0 zip, maybe in documents so you have permission to access it.
At the site you'll also find sample apks, apps, a FAQ, usage tips and a link to the google group for support etc.

Go back to eclipse and right click the workspace
1. New Android Project
2. Name it whatever you like, I do this: lib_abs
3. Check create project from existing source
4. Location: browse.. go to 4.0/library, then click open
5. Finish.

6. Right click the project - Properties -> Android then under the Library section, click  Add to add lib_abs as a library to our project. Click Apply, then OK
7. create /libs folder under in the Hello Mudroid folder
8. Drag and drop the maps jar into the /libs folder
	NOTE: If there are problems getting this to work on ADT 17, check out
	http://android.foxykeep.com/dev/how-to-fix-the-classdefnotfounderror-with-adt-17
9. Change the source file HelloMudroidActivity superclass form Activity to SherlockMapActivity
10. Add unimplemented method to the HelloMudroidActivity - isRouteDisplayed();
11. Add to AndroidManifest.xml Theme.Sherlock:

android:theme="@style/Theme.Sherlock" under the Application node

Run... on both 2.1 and 4.0.3

Checkpoint 2 - Adding a MapView to the layout and Activity
----------------------------------------------------------
1. Edit /res/layout/main.xml - to add a MapView

Delete:

```xml
<TextView
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    android:text="@string/hello" />
```

Add in it's place:

```xml
<com.google.android.maps.MapView
    android:id="@+id/map"
    android:layout_width="fill_parent"
    android:layout_height="fill_parent"
    android:apiKey="@string/maps_api_key"
    android:clickable="true" />
```

2. Open /res/values/strings.xml - and add your debug key.
	If you don't have a debug key yet, follow the instructions from the section:
	"Getting the MD5 Fingerprint of the SDK Debug Certificate" at:
	http://code.google.com/android/add-ons/google-apis/mapkey.html
	
NOTE: MAKE SURE YOU ADD IT TO strings.xml OR YOUR MAP WILL NOT APPEAR!!!

ADD THESE PERMISSIONS to AndroidManifest.xml (otherwise myLocationOverlay and maps WILL NOT WORK):

```xml
<uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
<uses-permission android:name="android.permission.INTERNET" />
```

Run... on both 2.1 and 4.0.3
Now we should see a map with the ABS actionbar

Go to HelloMudroidActivity.java to add extra functionality to the map.
3. We'll edit the file to
	A. get a handle on the map view to
	B. add zoom controls
	C. implement my location overlay

Take a quick tour of the Activity life cycle
http://developer.android.com/reference/android/app/Activity.html
onCreate, onPause and onResume are the most important methods to an Activity

Run... on both 2.1 and 4.0.3

Checkpoint 3 - Adjusting the ActionBar to improve the UI of the app and add final tweaks for hardware acceleration and different screen sizes
---------------------------------------------------------------------------------------------------------------------------------------------
Adding a see through black overlay actionbar and a find my location icon:

1. Create a my location icon for all screen densities at: http://code.google.com/p/android-ui-utils/
2. Move the icons to their correct folders under the /res folder
3. Create a new folder "menu" under /res
4. Right click in menu folder and create a new file "action_items.xml"
5. Edit the file by adding an item for mylocation
5. Add the string mylocation to strings.xml located in /values
6. Go to HelloMudroidActivity.java and add functionality to the action item
	A. add onCreateOptionsMenu
	B. add onOptionsItemSelected
	C. tie an action to the icon - centre and zoom

Run... on both 2.1 and 4.0.3

Now we'll change the actionbar to be semi-transparent:

7. Create a new file in /values named "colors.xml"
8. Add the color for the semi-transparent actionbar
9. Go back to HelloMudroidActivity.java and change onCreate to implement this new color for ABS
10. Lastly make the app use hw accel for 3.0+ and comapible with all types of screen sizes by adding to AndroidManifest.xml:

```xml
<supports-screens
    android:anyDensity="true"
    android:largeScreens="true"
    android:normalScreens="true"
    android:smallScreens="true"
    android:xlargeScreens="true" />
```

, within the manifest node and within the application tag:

```xml
android:hardwareAccelerated="true"
```

The tutorial is now complete.
Run... on both 2.1 and 4.0.3 and enjoy your first Android app :-)
