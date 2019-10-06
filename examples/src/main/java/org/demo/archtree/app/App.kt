package org.demo.archtree.app

import archknife.annotation.ProvideApplication
import archknife.extension.ArchknifeApplication
import archtree.helper.HelperModule

@ProvideApplication(externalModuleClasses = [HelperModule::class])
class App: ArchknifeApplication()