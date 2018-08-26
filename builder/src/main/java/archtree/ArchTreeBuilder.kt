package archtree

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.annotation.IdRes
import android.util.Log
import archtree.viewmodel.BaseViewModel

@Suppress("UNCHECKED_CAST")
abstract class ArchTreeBuilder<ViewModel : BaseViewModel, out Builder> {

    var layoutId: Int = -1
        private set

    var viewModelClass: Class<ViewModel>? = null
        private set
    var bindingKey = -1
        private set
    var skipViewModelInit: Boolean = false
        private set

    var dataBindingComponent: DataBindingComponent? = null
        private set
    var dataBindingComponentBindingKey: Int = -1
        private set

    var bundle: Bundle? = null
        private set
    var layer: ArchTreeLayer<ViewModel>? = null
        private set

    var toolbarViewId: Int? = null
        private set
    var toolbarTitle: String = ""
        private set
    var toolbarIcon: Int? = null
        private set

    @JvmOverloads
    open fun setToolbar(@IdRes viewId: Int = 0, title: String = "", icon: Int? = null): Builder {
        this.toolbarViewId = viewId
        this.toolbarTitle = title
        this.toolbarIcon = icon
        return this as Builder
    }

    open fun setLayoutId(layoutId: Int): Builder {
        this.layoutId = layoutId
        return this as Builder
    }

    @JvmOverloads
    open fun setViewModel(viewModelClass: Class<ViewModel>, bindingKey: Int = -1,
                          skipViewModelInit: Boolean = false): Builder {
        this.viewModelClass = viewModelClass
        this.bindingKey = bindingKey
        this.skipViewModelInit = skipViewModelInit
        return this as Builder
    }

    @SuppressLint("LogNotTimber")
    @JvmOverloads
    open fun setDatabindingComponent(dataBindingComponent: Any?, bindingKey: Int = -1): Builder {
        dataBindingComponentBindingKey = bindingKey

        try {
            this.dataBindingComponent = dataBindingComponent as? DataBindingComponent?
        } catch (e: ClassCastException) {
            Log.e(ArchTreeBuilder::class.java.name, e.message)
        }

        return this as Builder
    }

    @Deprecated("Use setToolbar(...) instead.")
    open fun setTitle(title: String): Builder {
        this.toolbarTitle = title
        return this as Builder
    }

    open fun setBundle(bundle: Bundle): Builder {
        this.bundle = bundle
        return this as Builder
    }

    /**
     * Internal method used to execute some common operations if the build() method of
     * ActivityBuilder/FragmentBuilder is called.
     *
     * @param layer custom layer which can be used to execute certain operation for the predefined
     * lifecycle methods.
     * @throws RuntimeException throws exception if the layout for this component is not set.
     * @since 1.0.0
     * @see ArchTreeLayer
     */
    protected open fun internalBuild(layer: ArchTreeLayer<ViewModel>) {
        this.layer = layer

        if (layoutId == -1) {
            throw RuntimeException("Did you forget to set the layout for this component?")
        }
    }
}