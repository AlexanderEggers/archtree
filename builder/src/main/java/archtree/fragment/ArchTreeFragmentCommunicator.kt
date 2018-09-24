package archtree.fragment

import android.content.Intent

interface ArchTreeFragmentCommunicator {
    fun onBackPressed(): Boolean
    fun onFragmentActivityResult(requestCode: Int, resultCode: Int, data: Intent?): Boolean
    fun onFragmentRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray): Boolean
}