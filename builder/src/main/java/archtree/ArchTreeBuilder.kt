package archtree

import android.annotation.SuppressLint
import androidx.databinding.DataBindingComponent
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.MenuRes
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
    var viewModelInitMode: ViewModelInitMode = ViewModelInitMode.FORCE_INIT
        private set

    var menuId: Int? = null
        private set

    var dataBindingComponent: DataBindingComponent? = null
        private set
    var dataBindingComponentBindingKey: Int = -1
        private set

    var lifecycleOwnerBindingKey: Int = -1
        private set

    var resourceBundle: Bundle? = null
        private set
    var layer: ArchTreeLayer<ViewModel>? = null
        private set

    var toolbarViewId: Int? = null
        private set
    var toolbarTitle: String? = null
        private set
    var toolbarIcon: Int? = null
        private set
    var displayHomeAsUpEnabled: Boolean = false
        private set
    var activityToolbar: Boolean = false
        private set

    @JvmOverloads
    open fun setToolbar(@IdRes viewId: Int? = null, title: String? = null,
                        displayHomeAsUpEnabled: Boolean = false, activityToolbar: Boolean = false,
                        @DrawableRes icon: Int? = null): Builder {
        this.toolbarViewId = viewId
        this.toolbarTitle = title
        this.displayHomeAsUpEnabled = displayHomeAsUpEnabled
        this.activityToolbar = activityToolbar
        this.toolbarIcon = icon
        return this as Builder
    }

    open fun setLayoutId(layoutId: Int): Builder {
        this.layoutId = layoutId
        return this as Builder
    }

    @JvmOverloads
    open fun setViewModel(viewModelClass: Class<ViewModel>, bindingKey: Int = -1,
                          viewModelInitMode: ViewModelInitMode = ViewModelInitMode.FORCE_INIT): Builder {
        this.viewModelClass = viewModelClass
        this.bindingKey = bindingKey
        this.viewModelInitMode = viewModelInitMode
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

    open fun bindLifecycleOwner(bindingKey: Int): Builder {
        lifecycleOwnerBindingKey = bindingKey
        return this as Builder
    }

    open fun setResourceBundle(bundle: Bundle?): Builder {
        this.resourceBundle = bundle
        return this as Builder
    }

    open fun setMenu(@MenuRes menuId: Int): Builder {
        this.menuId = menuId
        return this as Builder
    }

    /**
     * Internal method used to execute some common operations if the build() method of
     * ActivityBuilder/FragmentBuilder is called.
     *
     * @param layer custom layer which can be used to execute certain operation for the predefined
     * lifecycle methods.
     * @since 1.0.0
     * @see ArchTreeLayer
     */
    protected open fun internalBuild(layer: ArchTreeLayer<ViewModel>) {
        this.layer = layer
    }
}