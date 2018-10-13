package archtree.fragment

import android.content.Intent
import android.content.res.Configuration
import android.view.Menu
import android.view.MenuItem

interface ArchTreeFragmentCommunicator {
    fun onBackPressed(): Boolean
    fun onFragmentActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean
    fun onFragmentRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray): Boolean
    fun onFragmentConfigurationChanged(newConfig: Configuration?): Boolean
    fun onFragmentNewIntent(intent: Intent?): Boolean
    fun onFragmentCreateOptionsMenu(menu: Menu?): Boolean
    fun onOptionsItemSelected(item: MenuItem?): Boolean
}