# Archtree Helper
This artifact focuses on introducing several helper classes and method to reduce boilercode and simplies implementations.

## BindingAdapters
This artifact has several binding adapter to simplify layouts using databinding.

### View Accessibility click action
These binding adapter improve assigning a click (single or long) action accessibility value to a certain view.

* ***archtree_accessibilityClickAction (string)*** - requires a string and defines the actual spoken text for the single press by the accessibility framework (it is the part for "double tab for ...).
* ***archtree_accessibilityLongClickAction (string)*** - requires a string and defines the actual spoken text for the long press by the accessibility framework.
* ***archtree_accessibilityClickActionRes (integer)*** - requires an integer that represents the text and defines the actual spoken text for the single press by the accessibility framework (it is the part for "double tab for ...).
* ***archtree_accessibilityLongClickActionRes (integer)*** - requires an integer that represents the text and defines the actual spoken text for the long press by the accessibility framework.

### TextView html text
* ***archtree_htmlText (string)*** - this expects a string that defines a html format. make sure to only use Android-supported html tags.
* ***archtree_htmlTextAsync (boolean)*** - defines if the given text should be loaded async

### Custom TextView Font
* ***archtree_customFont (integer)*** - expects an integer that represents the font asset. This method can be useful when support an Android minSdkLevel that does not support the native fontFamily implementation by Android Studio.

### ImageView ImageDrawable
* ***archtree_drawableRes (integer)*** - expects an integer that represents a drawable and can only be set on a view that extends the ImageView. This binding method sets the value for the method ImageView.setImageDrawable.

### TextView Text Resource
* ***archtree_textRes (integer)*** - expects an integer that represents the text resource. This text resource will be assigned to the TextView.
* ***archtree_textResAsync (boolean)*** - defines if the given text should be loaded async

### AppCompatTextView Async Text
* ***archtree_asyncText (string)*** expects a string that will be assigned asynchronously to the TextView.

### TextView Text Color Resource
* ***archtree_textColorRes (integer)*** - expects an integer that represents a color resource. That color will be set for the text color of the given TextView.

### View Background Resource
* ***archtree_backgroundRes (integer)*** - expects an integer that represents a drawable resource. That color will be set for the background of the given View.

### ImageView Tint Color Resource
* ***archtree_imageTintColor (integer)*** - expects an integer that represents a color resource. That color will be set for the tint color for the given ImageView.

### View Notch Support
* ***archtree_viewHeightForNotch (boolean)*** - expects a boolean which should be true if the view is visible and requires to have notch support. This is relevant if the statusbar of the project is transparent and the app layout needs to adjust itself according to the given device notch. This method only works in compination with fitsSystemWindows that needs to be dragged from the root view to the related notch supporting view.

### View Visibility
* ***archtree_visibility (boolean)*** - expects a boolean. If true it will show the view. If false it will set the view as "GONE". When parsing null, nothing will happen.
* ***archtree_visibilityTransition (Transition)*** - expects a transition object that can be used when showing/hiding the view.

## Helper Classes
* AccessibiltyHelper - Helper methods to determine if a accessibilty service is enabled or not, including the ability to use livedata to observe any changes.
* BitmapAccessor - Helper to access bitmaps.
* ConnectivityHelper - Helper to determine the current network state.
* JsonHelper - Helper to access and work with jsons (serialization and deserialization)
* ResourceAccessor- Helper to access project related resource
* TimeHelper - Helper class to access the current times in millis. That should make unit tests much easier when working with this specific use case.

## View Helper Classes
* BoundedCoordinatorLayout
