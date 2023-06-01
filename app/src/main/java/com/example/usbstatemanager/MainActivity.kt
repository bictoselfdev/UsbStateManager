package com.example.usbstatemanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.usbstatemanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private var usbStateManager: UsbStateManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        usbStateManager = UsbStateManager(this)
        usbStateManager?.register(object : UsbStateManager.OnUsbStateListener {
            override fun onStateChanged(map: HashMap<String, Boolean>) {
                binding.tvConnected.text = map[UsbStateManager.USB_CONNECTED].toString()
                binding.tvHostConnected.text = map[UsbStateManager.USB_HOST_CONNECTED].toString()
                binding.tvConfigured.text = map[UsbStateManager.USB_CONFIGURED].toString()
                binding.tvFunctionAdb.text = map[UsbStateManager.USB_FUNCTION_ADB].toString()

                binding.tvFunctionMtp.text = map[UsbStateManager.USB_FUNCTION_MTP].toString()
                binding.tvFunctionRndis.text = map[UsbStateManager.USB_FUNCTION_RNDIS].toString()
                binding.tvFunctionMidi.text = map[UsbStateManager.USB_FUNCTION_MIDI].toString()
                binding.tvFunctionPtp.text = map[UsbStateManager.USB_FUNCTION_PTP].toString()

                binding.tvFunctionAudioSource.text = map[UsbStateManager.USB_FUNCTION_AUDIO_SOURCE].toString()
                binding.tvFunctionAccessory.text = map[UsbStateManager.USB_FUNCTION_ACCESSORY].toString()
                binding.tvFunctionNcm.text = map[UsbStateManager.USB_FUNCTION_NCM].toString()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()

        usbStateManager?.unregister()
    }
}