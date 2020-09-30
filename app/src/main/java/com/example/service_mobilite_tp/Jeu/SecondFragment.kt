package com.example.service_mobilite_tp.Jeu

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.example.service_mobilite_tp.R
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private var  codeScanner : CodeScanner? = null
    private val requestCodeCam = 4

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        codeScanner = CodeScanner(context!!, scanner_view)

        when {
            ContextCompat.checkSelfPermission(context!!, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
             scan()
            }
            else -> {
                // You can directly ask for the permission.
                requestPermissions(arrayOf(Manifest.permission.CAMERA),requestCodeCam)
            }
        }




        // Callbacks

    }



    private fun  scan( ){
        // You can use the API that requires the permission.
        // Parameters (default values)
        codeScanner!!.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner!!.formats = CodeScanner.ALL_FORMATS // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner!!.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner!!.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner!!.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner!!.isFlashEnabled = false // Whether to enable flash or not
        codeScanner!!.decodeCallback = DecodeCallback {
            activity?.runOnUiThread {
                Log.d("Camera", "Scan result: ${it.text}")
            }
        }
        codeScanner!!.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            activity?.runOnUiThread {
                Log.d("Camera", "Camera initialization error: ${it.message}")
            }
        }

        scanner_view.setOnClickListener {
            codeScanner!!.startPreview()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner!!.startPreview()
    }

    override fun onPause() {
        codeScanner!!.releaseResources()
        super.onPause()
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
           requestCodeCam -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() &&
                            grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                    scan()
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                }
                return
            }

            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }
}
