ArchTree
=====

[![Download](https://api.bintray.com/packages/mordag/android/archtree-core/images/download.svg) ](https://bintray.com/mordag/android/archtree-core/_latestVersion)

Android library which is a collection of APIs which can help you to simplify the development of your app. The focus of this library is to struture the architecture of your app by using different helper classes to introduce the MVVM pattern.

Download
--------
```gradle
repositories {
  jcenter()
}

dependencies {
  def archtree_version = "0.20.2"

  //includes all library artifacts (except testing)
  implementation "org.archtree:archtree-core:$archtree_version"
  
  //just builder (base, activity and fragment) and viewmodel classes
  implementation "org.archtree:archtree-builder:$archtree_version"
  //just viewmodel classes
  implementation "org.archtree:archtree-viewmodel:$archtree_version"
  //just action classes
  implementation "org.archtree:archtree-action:$archtree_version"
  //just helper classes
  implementation "org.archtree:archtree-helper:$archtree_version"
  //just databinding list classes
  implementation "org.archtree:archtree-list:$archtree_version"
  
  //just testing helper
  testImplementation "org.archtree:archtree-testing:$archtree_version"
}
```

How do I use ArchTree? (Step-by-step introduction for 0.20.2)
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
 * ArchTree requires Dagger-v2

Author
------
Alexander Eggers - [@mordag][2] on GitHub

License
-------
Apache 2.0. See the [LICENSE][1] file for details.


[1]: https://github.com/Mordag/archtree/blob/master/LICENSE
[2]: https://github.com/Mordag
[3]: https://github.com/Mordag/archtree/tree/master/examples
