ArchTree
=====

[![Download](https://api.bintray.com/packages/mordag/android/archtree-builder/images/download.svg) ](https://bintray.com/mordag/android/archtree-builder/_latestVersion)

Android library which is a collection of APIs which can help you to simplify the development of your app. The focus of this library is to struture the architecture of your app by using different helper classes to introduce the MVVM pattern.

Download
--------
```gradle
repositories {
  jcenter()
}

dependencies {
  def archtree_version = "0.24.0"
  
  //builder classes (base, activity and fragment) and viewmodel classes
  implementation "org.archtree:archtree-builder:$archtree_version"
  //viewmodel classes
  implementation "org.archtree:archtree-viewmodel:$archtree_version"
  //action classes
  implementation "org.archtree:archtree-action:$archtree_version"
  //helper core classes
  implementation "org.archtree:archtree-helper-core:$archtree_version"
  //helper dagger classes
  implementation "org.archtree:archtree-helper-dagger:$archtree_version"
  //databinding list classes
  implementation "org.archtree:archtree-list:$archtree_version"
  
  //testing helper
  testImplementation "org.archtree:archtree-testing:$archtree_version"
}
```

How do I use ArchTree? (Step-by-step introduction for 0.24.0)
-------------------
Coming soon! For now, use the [example project][3] as a reference.

Status
------
Version 1.0.0 is currently under development in the develop branch.

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


[1]: https://github.com/Mordag/archtree/blob/develop/LICENSE
[2]: https://github.com/Mordag
[3]: https://github.com/Mordag/archtree/tree/develop/examples
