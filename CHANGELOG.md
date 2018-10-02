Change Log
==========

Version 0.11.1 *(2018-09-25)*
----------------------------
- **NEW:** Tweaking default action click internal time.
- **NEW:** Added support for permission requests inside viewmodels. ViewModels have now a new method onRequestPermissionsResult. Each ArchTree component will retrieve permission results even if the request has been made using the ActivityCompat.
- **FIX:** Fixed onHiddenChanged not updating toolbar - onHiddenChanged will now use the app onResume.
- **FIX:** Fixed NoClassDefFound when using a specific forEach function.
- **MISC:** Added support for the latest Android Studio and Gradle Plugin version. I also updated the support library to version 28.0.0 and Kotlin to version 1.2.71.

Version 0.10.2 *(2018-09-15)*
----------------------------
- **NEW** Added option to preference fragment to change visiblity of preference entries.
- **BUGFIX**: Fixed several bugs inside preference fragment.

Version 0.10.0 *(2018-09-15)*
----------------------------
- **NEW:** Added support for PreferenceFragmentCompat.

Version 0.9.3 *(2018-09-15)*
----------------------------
- **NEW:** Added forceExecute method to the action object.
- **NEW:** Added helper method for retrieving assets.
- **NEW**: Added flag to control if toolbar is inside fragment or activity.
- **FIX:** Made Action.onExecute protected to avoid misuse of this method.
- **MISC:** Removed several CallSuper annotations.

Version 0.9.0 *(2018-08-29)*
----------------------------
- **NEW:** Prevent double click when using the action pattern. Each action will have 1000ms difference between clicks by default. You can change this value by using the method provideClickIntervalTime() which is provided by your abstract action implementation.
- **NEW:** Added more binding helper functions.
- **NEW:** Added public getter to check if a certain viewmodel has been initialised.
- **MISC:** Updated dagger and archknife dependencies.

Version 0.8.0 *(2018-08-26)*
----------------------------
- **BREAKING:** Tweaking list related binding adapter names.
- **BREAKING:** Moved several classes of the list artifact to a new subfolder.
- **BREAKING:** Removed bundle from BaseViewModel.onActivityResult
- **NEW:** Handling onActivityResult and onOptionsItemSelected for ArchTreeFragments
- **NEW:** Made toolbar setter available for fragments. ArchFragments will recreate the toolbar content using the onResume method.
- **NEW:** Added builder impl to add databinding components to the layout. This databinding component could be use for views that are created using databinding and require a databinding component instance in order to work. Inside the layout the databinding component has to be from type java.lang.Object, else the databinding processor won't reconize the type.
- **BUGFIX:** Tweaking binding methods to prevent layout crashes if using wrong res values
- **BUGFIX:** Ensured that only active and visible fragments can get backpress and activityresult events.

Version 0.7.0 *(2018-08-19)*
----------------------------
- **NEW:** Added new artifact 'testing'. This artifact will help you with testing your app. It provides you with a ArchTreeBaseTest. This artifact will get more useful helper and methods in the next pre-1.0.0 releases.
- **FIX:** Made some classes and functions non-final to simplify testing those classes in your own code.
- **FIX:** Fixed wrong return value inside the JsonConveter method convertJson.

Version 0.6.0 *(2018-08-12)*
----------------------------
- **NEW:** Added new artifact 'helper'. This artifact includes several util and databinding related classes to help you to improve your app quality. Take a look inside the related folder to get an idea (https://github.com/Mordag/archtree/tree/master/helper/src/main/java/archtree/helper)
- **NEW:** Added new artifact 'list'. This artifact can be used to create databindable list and connect those with your viewmodels using the given ObservableList. To bind your item source to a certain list view, you can use the attribute "archtree_itemSource". The item layout can be set using the attribute "archtree_itemLayout". There are two support list types inside this artifact: **BindableLinearLayout** and **BindableRecyclerViewLayout**
- **NEW:** Added several new builder methods for the toolbar, ui visibility, menu and databinding component.
- **NEW:** The BaseViewModel got some new additions as well. You can now override the onBackPressed and onOptionsItemSelected inside this class. Those methods will be called from the underlying Android native implementation. That should help you to reduce your code from the activity classes.
- **BUGFIX**: Added missing getView to the ArchTreeFragment class
- **BUGFIX**: Fixed the visibility of serveral methods.
- **BREAKING CHANGES**: All databinding related attributes has been changed their name to "archtree_(oldName)" to prevent name clashing with other libraries.

Version 0.5.0 *(2018-07-03)*
----------------------------
- **NEW:** Added builder function to hide the support bar.
- **FIX:** Fixed visibility for different functions.
- **FIX:** Removed HasNoViewModel. This object will not be used by any project implementation due to dagger restrictions.
- **MISC:** Added missing databinding kapt usage.
- **MISC:** Reduced min SDK level to 14.

Version 0.4.0 *(2018-05-13)*
----------------------------
- Several changes to the dependencies of the library to adopt the library to the latest changes made by several external libraries.
- Merged archtree-activity and archtree-fragment into archtree-builder.

Version 0.3.0 *(2018-05-12)*
----------------------------

- Added new artifact 'archtree-action' which can be used to add onClick actions to a certain view using the MVVM pattern. A certain action can be bound to a view by using the binding command 'action' and 'actionParamter'. That means all onClick logic for views can be handled within ViewModels!
- Moved archtree package from org.archtree.* to archtree.*
- Updated Kotlin to support version 1.2.41.

Version 0.2.0 *(2018-04-27)*
----------------------------

- Moved setFragmentFlow to the ActivityBuilder only. Fragments are not supporting the HasFragmentFlow implementation.
- Added missing abstract and open attributes for the ArchTreeLayer, ActivityLayer and FragmentLayer.
- Fixed bug inside the ArchTreeActivity using the autotarget library and it's default show-fragment implementation. The related interface HasFragmentFlow has been changed by the library to return a boolean which indicates if the interface has been fully implemented or not. If a specfic activity has no HasFragmentFlow object set by the builder pattern, then the method will return false.

Version 0.1.0 *(2018-04-26)*
----------------------------

- Initial library release.
