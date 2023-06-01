# UsbStateManager
BroadcastReceiver를 활용하여 USB 상태를 가져오는 예제입니다.

USB 상태
 - USB_CONNECTED : USB 연결 상태
 - USB_HOST_CONNECTED : USB 호스트로 연결 상태
 - USB_CONFIGURED : USB 연결 시 구성이 완료되었는지 여부
 - USB_FUNCTION_ADB : USB 디버깅 기능 활성화 여부
 - USB_FUNCTION_MTP : 파일 전송 기능 활성화 여부
 - USB_FUNCTION_RNDIS : USB 테더링 기능 활성화 여부
 - USB_FUNCTION_MIDI : 악기와 연결하여 사용하는 기능 활성화 여부
 - USB_FUNCTION_PTP : 이미지 전송 기능 활성화 여부
 - USB_FUNCTION_AUDIO_SOURCE : 오디오 기능 활성화 여부
 - USB_FUNCTION_ACCESSORY : 액세서리 모드 기능 활성화 여부
 - USB_FUNCTION_NCM : 공장 OS 기능 활성화 여부



해당 예제는 아래 구글 오픈소스를 참조하여 작성하였습니다.


https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/hardware/usb/UsbManager.java


자세한 설명은 아래 블로그 링크를 통해서 확인 가능합니다.


[[안드로이드] USB 상태 가져오기 : UsbStateManger](https://bictoselfdev.blogspot.com/2023/05/practiceRedux.html](https://bictoselfdev.blogspot.com/2023/05/UsbStateManager.html)
