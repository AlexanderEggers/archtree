ArchTree
=====

[![Download](https://api.bintray.com/packages/mordag/android/archtree-core/images/download.svg) ](https://bintray.com/mordag/android/archtree-core/_latestVersion)

Android library is a collection of APIs which can help you to simplify the development of your app. The focus of this library is to struture the architecture of your app by using different helper classes to introduce the MVVM pattern.

Download
--------
```gradle
repositories {
  jcenter()
}

dependencies {
  //includes all library artifacts
  implementation 'org.archtree:archtree-core:0.4.0'
  
  //just archtree builder (base, activity and fragment)
  implementation 'org.archtree:archtree-builder:0.4.0'
  //just archtree viewmodel classes
  implementation 'org.archtree:archtree-viewmodel:0.4.0'
  //just action classes
  implementation 'org.archtree:archtree-action:0.4.0'
}
```

How do I use ArchTree? (Step-by-step introduction for 0.4.0)
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

Author
------
Alexander Eggers - [@mordag][2] on GitHub

License
-------
Apache 2.0. See the [LICENSE][1] file for details.


[1]: https://github.com/Mordag/archtree/blob/master/LICENSE
[2]: https://github.com/Mordag
[3]: https://github.com/Mordag/archtree/tree/master/examples
