ArchTree
=====

[![Download](https://api.bintray.com/packages/mordag/android/archtree-core/images/download.svg) ](https://bintray.com/mordag/android/archtree-core/_latestVersion)

Android library that simplifies the usage of Activities, Fragments and ViewModels inside your application.

Download
--------
```gradle
repositories {
  //jcenter() Coming soon!
  maven { url  "https://dl.bintray.com/mordag/android" }
}

dependencies {
  //includes all library artifacts
  implementation 'org.archtree:archtree-core:0.2.0'
  
  //just archtree builder base
  implementation 'org.archtree:archtree-builder:0.2.0'
  //just archtree activity classes
  implementation 'org.archtree:archtree-activity:0.2.0'
  //just archtree fragment classes
  implementation 'org.archtree:archtree-fragment:0.2.0'
  //just archtree viewmodel classes
  implementation 'org.archtree:archtree-viewmodel:0.2.0'
}
```

How do I use ArchTree? (Step-by-step introduction for 0.2.0)
-------------------
Coming soon! For now, use the [example project][3] as a reference.

Status
------
Version 1.0.0 is still in development.

Comments/bugs/questions/pull requests are always welcome!

Compatibility
-------------

 * **Minimum Android SDK**: ArchTree requires a minimum API level of 16.
 * ArchTree requires Binding-v2.
 * ArchTree requires Dagger-v2 (at least version 2.15).
 
TODO
-------------
* Testing
* Documentation

Author
------
Alexander Eggers - [@mordag][2] on GitHub

License
-------
Apache 2.0. See the [LICENSE][1] file for details.


[1]: https://github.com/Mordag/archtree/blob/master/LICENSE
[2]: https://github.com/Mordag
[3]: https://github.com/Mordag/archtree/tree/master/examples
