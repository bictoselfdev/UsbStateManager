package com.example.usbstatemanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter

/**
 * UsbManager class reference
 *
 * https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/hardware/usb/UsbManager.java
 */

class UsbStateManager(private val context: Context) {

    private val usbStateMap = HashMap<String, Boolean>()

    private var callback: OnUsbStateListener? = null

    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val action = intent.action
            try {
                if (action == ACTION_USB_STATE) { // USB 상태 수신!!
                    intent.extras?.run {

                        usbStateMap[USB_CONNECTED] = getBoolean(USB_CONNECTED)
                        usbStateMap[USB_HOST_CONNECTED] = getBoolean(USB_HOST_CONNECTED)
                        usbStateMap[USB_CONFIGURED] = getBoolean(USB_CONFIGURED)
                        usbStateMap[USB_FUNCTION_ADB] = getBoolean(USB_FUNCTION_ADB)
                        usbStateMap[USB_FUNCTION_RNDIS] = getBoolean(USB_FUNCTION_RNDIS)
                        usbStateMap[USB_FUNCTION_MTP] = getBoolean(USB_FUNCTION_MTP)
                        usbStateMap[USB_FUNCTION_PTP] = getBoolean(USB_FUNCTION_PTP)
                        usbStateMap[USB_FUNCTION_AUDIO_SOURCE] = getBoolean(USB_FUNCTION_AUDIO_SOURCE)
                        usbStateMap[USB_FUNCTION_MIDI] = getBoolean(USB_FUNCTION_MIDI)
                        usbStateMap[USB_FUNCTION_ACCESSORY] = getBoolean(USB_FUNCTION_ACCESSORY)
                        usbStateMap[USB_FUNCTION_NCM] = getBoolean(USB_FUNCTION_NCM)

                        callback?.onStateChanged(usbStateMap)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun register(listener: OnUsbStateListener) {
        callback = listener

        val intentFilter = IntentFilter()
        intentFilter.addAction(ACTION_USB_STATE)
        context.registerReceiver(receiver, intentFilter)
    }

    fun unregister() {
        try {
            context.unregisterReceiver(receiver)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    interface OnUsbStateListener {
        fun onStateChanged(map: HashMap<String, Boolean>)
    }

    companion object {
        /**
         * USB 관련 Broadcast Actions
         *
         * ACTION_USB_STATE : USB 상태 수신을 위한 고정 Broadcast Action
         */
        const val ACTION_USB_STATE = "android.hardware.usb.action.USB_STATE"

        /**
         * USB 상태 수신 extras
         *
         * USB_CONNECTED : USB 연결 상태
         * USB_HOST_CONNECTED : USB 호스트로 연결 상태
         * USB_CONFIGURED : USB 연결 시 구성이 완료되었는지 여부
         *
         * USB_FUNCTION_ADB : USB 디버깅 기능 활성화 여부
         * USB_FUNCTION_MTP : 파일 전송 기능 활성화 여부
         * USB_FUNCTION_RNDIS : USB 테더링 기능 활성화 여부
         * USB_FUNCTION_MIDI : 악기와 연결하여 사용하는 기능 활성화 여부
         * USB_FUNCTION_PTP : 이미지 전송 기능 활성화 여부
         *
         * USB_FUNCTION_AUDIO_SOURCE : 오디오 기능 활성화 여부
         * USB_FUNCTION_ACCESSORY : 액세서리 모드 기능 활성화 여부
         * USB_FUNCTION_NCM : 공장 OS 기능 활성화 여부
         */
        const val USB_CONNECTED = "connected"
        const val USB_HOST_CONNECTED = "host_connected"
        const val USB_CONFIGURED = "configured"
        const val USB_FUNCTION_ADB = "adb"
        const val USB_FUNCTION_MTP = "mtp"
        const val USB_FUNCTION_RNDIS = "rndis"
        const val USB_FUNCTION_MIDI = "midi"
        const val USB_FUNCTION_PTP = "ptp"
        const val USB_FUNCTION_AUDIO_SOURCE = "audio_source"
        const val USB_FUNCTION_ACCESSORY = "accessory"
        const val USB_FUNCTION_NCM = "ncm"
    }
}